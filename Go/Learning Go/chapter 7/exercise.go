package main

import (
	"fmt"
	"io"
	"os"
	"sort"
)

type Ranker interface {
	Ranking() []string
}

type Team struct {
	Name    string
	Players []string
}

type League struct {
	Name  string
	Teams map[string]Team
	Wins  map[string]int
}

func (league *League) MatchResult(team1 string, score1 int, team2 string, score2 int) {
	if _, ok := league.Teams[team1]; !ok {
		return
	}
	if _, ok := league.Teams[team2]; !ok {
		return
	}

	if score1 == score2 {
		return
	}

	if score1 > score2 {
		league.Wins[team1]++
	}
	if score1 < score2 {
		league.Wins[team2]++
	}
}

func (league *League) Ranking() []string {
	names := make([]string, 0, len(league.Teams))

	for k := range league.Wins {
		names = append(names, k)
	}

	sort.Slice(names, func(first, second int) bool {
		return league.Wins[names[first]] > league.Wins[names[second]]
	})
	return names
}

func RankPrinter(ranker Ranker, writer io.Writer) {
	results := ranker.Ranking()

	for _, val := range results {
		_, err := io.WriteString(writer, val)
		_, err = writer.Write([]byte("\n"))

		if err != nil {
			return
		}
	}
}

func exerciseMain() {
	league := League{
		Name: "BigLeague",
		Teams: map[string]Team{
			"Italy": {
				Name:    "Italy",
				Players: []string{"Player1", "Player2", "Player3", "Player4", "Player5"},
			},
			"France": {
				Name:    "France",
				Players: []string{"Player1", "Player2", "Player3", "Player4", "Player5"},
			},
			"India": {
				Name:    "India",
				Players: []string{"Player1", "Player2", "Player3", "Player4", "Player5"},
			},
			"Nigeria": {
				Name:    "Nigeria",
				Players: []string{"Player1", "Player2", "Player3", "Player4", "Player5"},
			},
		},
		Wins: map[string]int{},
	}

	league.MatchResult("Italy", 50, "France", 70)
	league.MatchResult("India", 85, "Nigeria", 80)
	league.MatchResult("Italy", 60, "India", 55)
	league.MatchResult("France", 100, "Nigeria", 110)
	league.MatchResult("Italy", 65, "Nigeria", 70)
	league.MatchResult("France", 95, "India", 80)

	results := league.Ranking()
	fmt.Println(results)

	RankPrinter(&league, os.Stdout)
}
