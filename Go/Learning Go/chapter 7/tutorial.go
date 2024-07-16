package main

import (
	"fmt"
	"math"
)

type Shape interface {
	Area() float64
	Perimeter() float64
}

type Polygon interface {
	GetSides() float64
}

type Rectangle struct {
	width, height, sides float64
}

type Circle struct {
	radius float64
}

func (cir Circle) Area() float64 {
	return math.Pi * cir.radius * cir.radius
}

func (cir Circle) Perimeter() float64 {
	return 2 * math.Pi * cir.radius
}

func (rec Rectangle) Area() float64 {
	return rec.height * rec.width
}

func (rec Rectangle) Perimeter() float64 {
	return 2 * (rec.width + rec.height)
}

func (rec Rectangle) GetSides() float64 {
	return rec.sides
}

func summary(shape Shape) {
	switch x := shape.(type) {
	case Circle:
		fmt.Printf("My type is %T \n", x)
	case Rectangle:
		fmt.Printf("My type is %T \n", x)
	default:
		fmt.Printf("My type is %T \n", x)
	}
}

func calculate() {
	rec := Rectangle{
		width:  10,
		height: 5,
		sides:  4,
	}
//	cir := Circle{radius: 5}
	summary(rec)
//	summary(cir)
}
