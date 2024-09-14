package main

import (
	"fmt"
	"github.com/go-git/go-git/v5"
	"github.com/go-git/go-git/v5/plumbing/object"
	"sort"
	"time"
)

const (
	outOfRange           = 99999
	daysInLastSixMonths  = 183
	weeksInLastSixMonths = 26
)

type column []int

func stats(email string) {
	commits := processRepositories(email)
	printCommitStats(commits)
}

func processRepositories(email string) map[int]int {
	filePath := getDotFilePath()
	repos := parseFileLinesToSlice(filePath)
	daysInMap := daysInLastSixMonths

	commits := make(map[int]int, daysInMap)
	for i := daysInMap; i > 0; i-- {
		commits[i] = 0
	}
	for _, path := range repos {
		commits = fillCommits(email, path, commits)
	}
	return commits
}

func fillCommits(email string, path string, commits map[int]int) map[int]int {
	repo, err := git.PlainOpen(path)
	if err != nil {
		panic(err)
	}
	ref, err := repo.Head()
	if err != nil {
		panic(err)
	}
	iterator, err := repo.Log(&git.LogOptions{From: ref.Hash()})
	if err != nil {
		panic(err)
	}

	offset := calcOffSet()
	err = iterator.ForEach(func(cmt *object.Commit) error {
		return processCommit(cmt, email, offset, commits)
	})
	if err != nil {
		panic(err)
	}
	return commits
}

func processCommit(cmt *object.Commit, email string, offset int, commits map[int]int) error {
	daysAgo := countDaysSinceDate(cmt.Author.When) + offset
	if cmt.Author.Email != email {
		return nil
	}
	if daysAgo != outOfRange {
		commits[daysAgo]++
	}
	return nil
}

func countDaysSinceDate(date time.Time) int {
	days := 0
	now := getBeginningOfDay(time.Now())

	for date.Before(now) {
		date = date.Add(time.Hour * 24)
		days++

		if days > daysInLastSixMonths {
			return outOfRange
		}
	}
	return days
}

func getBeginningOfDay(dayTime time.Time) time.Time {
	year, month, day := dayTime.Date()
	startOfDay := time.Date(year, month, day, 0, 0, 0, 0, dayTime.Location())
	return startOfDay
}

func calcOffSet() int {
	var offSet int
	weekday := time.Now().Weekday()

	switch weekday {
	case time.Sunday:
		offSet = 7
	case time.Monday:
		offSet = 6
	case time.Tuesday:
		offSet = 5
	case time.Wednesday:
		offSet = 4
	case time.Thursday:
		offSet = 3
	case time.Friday:
		offSet = 2
	case time.Saturday:
		offSet = 1
	}
	return offSet
}

func printCommitStats(commits map[int]int) {
	keys := sortMapIntoSlice(commits)
	cols := buildCols(keys, commits)
	printCells(cols)
}

func sortMapIntoSlice(commitMap map[int]int) []int {
	var keys []int
	for k := range commitMap {
		keys = append(keys, k)
	}
	sort.Ints(keys)
	return keys
}

func buildCols(keys []int, commits map[int]int) map[int]column {
	cols := make(map[int]column)
	col := column{}

	for _, key := range keys {
		week := key / 7
		dayInWeek := key % 7

		if dayInWeek == 0 {
			col = column{}
		}
		col = append(col, commits[key])
		if dayInWeek == 6 {
			cols[week] = col
		}
	}
	return cols
}

func printCells(cols map[int]column) {
	printMonths()

	for j := 6; j >= 0; j-- {
		printDayRow(j, cols)
	}
}

func printDayRow(dayIndex int, cols map[int]column) {
	for i := weeksInLastSixMonths + 1; i >= 0; i-- {
		if i == weeksInLastSixMonths+1 {
			printDayCol(dayIndex)
		}
		printWeekCell(i, dayIndex, cols)
	}
	fmt.Printf("\n")
}

func printWeekCell(weekIndex, dayIndex int, cols map[int]column) {
	if col, ok := cols[weekIndex]; ok {
		if weekIndex == 0 && dayIndex == calcOffSet()-1 {
			printCell(col[dayIndex], true)
		} else if len(col) > dayIndex {
			printCell(col[dayIndex], false)
		} else {
			printCell(0, false)
		}
	} else {
		printCell(0, false)
	}
}

func printMonths() {
	week := getBeginningOfDay(time.Now()).Add(-(daysInLastSixMonths * time.Hour * 24))
	month := week.Month()

	fmt.Printf("        ")
	for {
		if week.Month() != month {
			fmt.Printf("%s ", week.Month().String()[:3])
			month = week.Month()
		} else {
			fmt.Printf("    ")
		}

		week = week.Add(7 * time.Hour * 24)
		if week.After(time.Now()) {
			break
		}
	}
	fmt.Printf("\n")
}

func printDayCol(day int) {
	out := "     "
	switch day {
	case 1:
		out = " Mon "
	case 3:
		out = " Wed "
	case 5:
		out = " Fri "
	}

	fmt.Printf(out)
}

func printCell(val int, today bool) {
	escape := "\033[0;37;30m"
	switch {
	case val > 0 && val < 5:
		escape = "\033[1;30;47m"
	case val >= 5 && val < 10:
		escape = "\033[1;30;43m"
	case val >= 10:
		escape = "\033[1;30;42m"
	}

	if today {
		escape = "\033[1;37;45m"
	}

	if val == 0 {
		fmt.Printf(escape + "  - " + "\033[0m")
		return
	}

	str := "  %d "
	switch {
	case val >= 10:
		str = " %d "
	}
	fmt.Printf(escape+str+"\033[0m", val)
}
