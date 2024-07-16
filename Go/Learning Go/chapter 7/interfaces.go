package main

import "io"

type Incrementer interface {
	Increment()
}

type LogicProvider struct {
}

func (logicProvider LogicProvider) Process(data string) string {
	return "business logic"
}

type OldLogic interface {
	Process(data string) string
}

type Client struct {
	L OldLogic
}

func (client Client) Program() {
	// get data from somewhere
	data := "got the data"
	client.L.Process(data)
}

func process(r io.Reader) error {
	return nil
}



func methodOne() {
	c := Client{L: LogicProvider{}}
	c.Program()
}
