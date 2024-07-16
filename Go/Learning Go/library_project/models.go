package main

import "fmt"

type Book struct {
	BaseMedia
	author string
}

type Magazine struct {
	BaseMedia
	issueNumber int
}

type Movie struct {
	BaseMedia
	duration int
}

func borrowItem(media Media) {
	err := media.Borrow()
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Printf("You borrowed %s \n", media.Title())
	}
}

func returnItem(media Media) {
	err := media.Return()
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Printf("You returned %s \n", media.Title())
	}
}

func checkStatus(media Media) {
	fmt.Printf("This item is %s \n", media.Status())
}

func caller() {
	book := Book{
		BaseMedia{title: "1984"}, "George Orwell",
	}
	magazine := Magazine{
		BaseMedia{title: "National Geographic"}, 202,
	}
	movie := Movie{
		BaseMedia{title: "Inception"}, 148,
	}

	mediaItems := []Media{&book, &magazine, &movie}

	for _, item := range mediaItems {
		checkStatus(item)
		borrowItem(item)
		checkStatus(item)
		returnItem(item)
		checkStatus(item)
		fmt.Println()
	}
}
