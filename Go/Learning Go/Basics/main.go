package main

import (
	"fmt"
	"math/rand"
)

const z = 20 * 10

type person struct {
	name string
	age  int
	pet  string
}

func main() {
	println()

	exercise5()
}

func blankSwitch() {
	words := []string{"a", "cow", "smile", "gopher", "octopus", "anthropologists"}

	for _, word := range words {
		switch wordLen := len(word); {
		case wordLen < 5:
			fmt.Println("length is too short")
		case wordLen > 10:
			fmt.Println("length too long")
		default:
			fmt.Println("just the right length")
		}
	}
}

func switchStatements() {
	words := []string{"a", "cow", "smile", "gopher", "octopus", "anthropologists"}

outer:
	for _, word := range words {
		switch size := len(word); size {
		case 1, 2, 3, 4:
			fmt.Println(word, "the length is too short")
		case 5:
			wordLength := len(word)
			fmt.Println(word, "this is just the right length", wordLength)
			break outer
		case 6, 7, 8, 9:
		default:
			fmt.Println("word is too long")
		}
	}
}

func labels() {
	samples := []string{"hello", "apple_n!"}

outer:
	for _, sample := range samples {
		for i, r := range sample {
			fmt.Println(i, r, string(r))
			if r == 'l' {
				continue outer
			}
		}
		fmt.Println()
	}
}

func forRange() {
	m := map[string]int{
		"a": 1,
		"b": 2,
		"c": 3,
	}

	for i := 0; i < 3; i++ {
		fmt.Println("Loop ", i)
		for k, v := range m {
			fmt.Println(k, v)
		}
	}
}

func shadowingVar() {

	if n := rand.Intn(10); n == 0 {
		fmt.Println("that's too low")
	} else if n > 5 {
		fmt.Println("that's too big")
	} else {
		fmt.Println("that's a good number")
	}
}

func anonStructs() {

	type firstPerson struct {
		name string
		age  int
	}

	f := firstPerson{
		name: "Bob",
		age:  44,
	}

	var g struct {
		name string
		age  int
	}

	g.name = "Bob"
	g.age = 44

	fmt.Println(f == g)

	var some struct {
		name string
		age  int
		pet  string
	}

	some.name = "bob"
	some.age = 23
	some.pet = "dog"

	pet := struct {
		name string
		kind string
	}{
		name: "Fido",
		kind: "dog",
	}

	fmt.Println(pet)
}

func structs() {

	bob := person{
		name: "parth",
		age:  21,
	}

	bob.name = "bob"
	fmt.Println(bob)
}

func mapAsSets() {
	intSet := map[int]bool{}
	vals := []int{5, 3, 2, 5, 20, 22, 11, 4, 2, 5, 8}

	for _, v := range vals {
		intSet[v] = true
	}

	fmt.Println(len(vals), len(intSet))
	fmt.Println(intSet[5])
	fmt.Println(intSet[100])
	if intSet[100] {
		fmt.Println("100 is in the intSet")
	}
}

func mapUsed() {
	totalWins := map[string]int{
		"Orcas": 1,
		"Lions": 2,
	}

	fmt.Println(totalWins["Orcas"])
	totalWins["Kittens"]++
	fmt.Println(totalWins["Kittens"])
	totalWins["Lions"] = 3
	fmt.Println(totalWins["Lions"])

	delete(totalWins, "Kittens")

	fmt.Println(totalWins)
}

func maps() {
	a := map[string]int{}
	fmt.Println(a)

	teams := map[string][]string{
		"Orcas":   {"fred", "ralph", "bijou"},
		"Lions":   {"joey", "peter", "billie"},
		"Kittens": {"waldo", "alex", "tom"},
	}

	test := make(map[string]string, 10)

	fmt.Println(len(teams))
	fmt.Println(test == nil)

	var mapped map[string]string
	fmt.Println(mapped == nil)
}
