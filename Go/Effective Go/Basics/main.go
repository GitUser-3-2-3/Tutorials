package main

import (
	"fmt"
)

const z = 20 * 10

type person struct {
	name string
	age  int
	pet  string
}

func main() {
	println()

	anonStructs()
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
