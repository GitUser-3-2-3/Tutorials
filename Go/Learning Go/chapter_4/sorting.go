package main

import (
	"fmt"
	"sort"
)

func sorting() {
	type Person struct {
		firstname string
		lastname  string
		age       int
	}

	people := []Person{
		{"Pat", "Patterson", 37},
		{"Tracy", "Bob_daughter", 23},
		{"Fred", "Fred_son", 18},
	}

	fmt.Println(people)

	sort.Slice(people, func(i, j int) bool {
		return people[i].lastname < people[j].lastname
	})

	fmt.Println(people)

	sort.Slice(people, func(i, j int) bool {
		return people[i].age < people[j].age
	})

	fmt.Println(people)
}
