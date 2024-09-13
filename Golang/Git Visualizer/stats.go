package main

import (
	"github.com/src-d/go-git"
	"gopkg.in/src-d/go-git.v4/plumbing/object"
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
