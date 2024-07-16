package main

import (
	"fmt"
	"time"
)

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

type Counter struct {
	total       int
	lastUpdated time.Time
}

func (counter *Counter) Increment() {
	counter.total++
	counter.lastUpdated = time.Now()
}

func (counter *Counter) String() string {
	return fmt.Sprintf(
		"total: %d, last updated: %v", counter.total, counter.lastUpdated,
	)
}

func doUpdateWrong(counter Counter) {
	counter.Increment()
	fmt.Println("in doUpdateWrong: ", counter.String())
}

func doUpdateRight(counter *Counter) {
	counter.Increment()
	fmt.Println("in doUpdateRight: ", counter.String())
}

type Adder struct {
	start int
}

func (add Adder) AddTo(val int) int {
	return add.start + val
}

type MailCategory int

const (
	Uncategorized MailCategory = iota
	Personal
	Spam
	Social
	Advertisements
)

type Employee struct {
	Name string
	Id   int
}

func (employee Employee) Description() string {
	return fmt.Sprintf("%s %d", employee.Name, employee.Id)
}

type Manager struct {
	Employee
	Reports []Employee
}

func (manager Manager) FindNewEmployees() []Employee {
	// do business logic
	return nil
}

type Inner struct {
	A int
}

func (i Inner) IntPrinter(val int) string {
	return fmt.Sprintf("Inner: %d", val)
}

func (i Inner) Double() string {
	return i.IntPrinter(i.A * 2)
}

type Outer struct {
	Inner
	S string
}

func (o Outer) IntPrinter(val int) string {
	return fmt.Sprintf("Outer: %d", val)
}
