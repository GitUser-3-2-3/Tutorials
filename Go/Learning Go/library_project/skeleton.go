package main

import (
	"fmt"
)

type Media interface {
	Title() string
	Borrow() error
	Return() error
	Status() string
}

type BaseMedia struct {
	title    string
	borrowed bool
}

func (bMedia *BaseMedia) Title() string {
	return bMedia.title
}

func (bMedia *BaseMedia) Borrow() error {

	if !bMedia.borrowed {
		bMedia.borrowed = true
		return nil
	}
	return fmt.Errorf("%s is borrowed \n", bMedia.title)
}

func (bMedia *BaseMedia) Return() error {

	if bMedia.borrowed {
		bMedia.borrowed = false
		return nil
	}
	return fmt.Errorf("%s is not borrowed \n", bMedia.title)
}

func (bMedia *BaseMedia) Status() string {

	if bMedia.borrowed {
		return "borrowed"
	}
	return "Available"
}
