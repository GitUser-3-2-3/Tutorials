package main

import (
	"fmt"
	"math/rand"
)

const DEFAULT = 89024

func exercise3() {
	type Employee struct {
		firstname string
		lastname  string
		id        int
	}

	first := Employee{
		"parth",
		"srivastav",
		21234890,
	}

	second := Employee{
		firstname: "juhi",
		lastname:  "joshi",
		id:        248900,
	}

	fmt.Println(DEFAULT)

	var third Employee
	third.firstname = "firstname"
	third.lastname = "lastname"
	third.id = 290348

	fmt.Println(first)
	fmt.Println(second)
	fmt.Println(third)
}

func exercise1() {

	greetings := []string{"hello1", "hello2", "hello3", "hello4", "hello5"}
	x := greetings[:2]
	y := greetings[1:4:4]
	s := greetings[3:5:5]

	fmt.Println(x)
	fmt.Println(y)
	fmt.Println(s)
}

func exercise4() {
	store := make([]int, 0, 100)

	for i := 1; i < 100; i++ {
		n := rand.Intn(100)
		store = append(store, n)
	}

	fmt.Println(store, len(store))

	for _, val := range store {
		switch {
		case val%2 == 0:
			fmt.Println("two")
		case val%3 == 0:
			fmt.Println("three")
		case val%2 == 0 && val%3 == 0:
			fmt.Println("six")
		default:
			fmt.Println("Never mind")
		}
	}
}

func exercise5() {
	var total int

	for i := 0; i < 10; i++ {
		total = total + i
		fmt.Println(total)
	}

	fmt.Println(total)
}