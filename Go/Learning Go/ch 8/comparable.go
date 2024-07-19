package main

import "fmt"

type Thinger interface {
	Thing()
}

type ThingerInt int

func (t ThingerInt) Thing() {
	fmt.Println("ThingInt: ", t)
}

type ThingerSlice []int

func (t ThingerSlice) Thing() {
	fmt.Println("ThingerSlice: ", t)
}

func Compare[T comparable](t1, t2 T) {
	if t1 == t2 {
		fmt.Println("equal!")
	}
}

func fourthMain() {
	var a int = 10
	var b int = 20
	Compare(a, b)

	var s ThingerInt = 10
	var d ThingerInt = 13
	Compare(s, d)

	var k ThingerSlice = []int{2, 3, 4, 5}
	var w ThingerSlice = []int{23, 42, 3, 4}

	var r Thinger = s
	var u Thinger = d
	r = k
	u = w
	Compare(r, u)

}
