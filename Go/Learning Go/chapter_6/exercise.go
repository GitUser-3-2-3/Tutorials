package main

import "fmt"

type Person struct {
	firstname string
	lastname  string
	age       int
}

func makePerson(firstname, lastname string, age int) Person {
	return Person{
		firstname: firstname,
		lastname:  lastname,
		age:       age,
	}
}

func makePersonPointer(firstname, lastname string, age int) *Person {
	return &Person{
		firstname: firstname,
		lastname:  lastname,
		age:       age,
	}
}

func updateSlice(slice []string, str string) {
	slice[len(slice)-1] = str
	fmt.Println(slice, " from update slice")
}

func growSlice(slice []string, str string) {
	slice = append(slice, str)
	fmt.Println(slice, " from grow slice")
}

func executeGOGC() {
	var people []Person
	for i := 0; i < 10_00_000; i++ {
		people = append(people, makePerson("fred", "peterson", 23))
	}
}
