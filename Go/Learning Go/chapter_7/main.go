package main

import "fmt"

type Person struct {
	firstname string
	lastname  string
	age       int
}

func (person Person) String() string {
	return fmt.Sprintf(
		"name: %s %s, age %d", person.firstname, person.lastname, person.age,
	)
}

func main() {
	println()

	person := Person{
		firstname: "parth",
		lastname:  "srivastav",
		age:       21,
	}

	output := person.String()
	fmt.Println(output)
}
