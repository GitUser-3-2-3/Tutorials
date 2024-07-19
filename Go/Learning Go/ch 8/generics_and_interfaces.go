package main

import (
	"fmt"
	"math"
)

type Pair[T fmt.Stringer] struct {
	Val1 T
	Val2 T
}

type Differ[T any] interface {
	fmt.Stringer
	Diff(T) float64
}

func myFindCloser[T Differ[T]](pair1, pair2 Pair[T]) Pair[T] {
	d1 := pair1.Val1.Diff(pair1.Val2)
	d2 := pair1.Val1.Diff(pair2.Val2)

	if d1 < d2 {
		return pair1
	}
	return pair2
}

type Point2D struct {
	X, Y int
}

func (point Point2D) String() string {
	return fmt.Sprintf("{%d, %d}", point.X, point.Y)
}

func (point Point2D) Diff(from Point2D) float64 {
	x := point.X - from.X
	y := point.Y - from.Y
	return math.Sqrt(float64(x*x) + float64(y*y))
}

type Point3D struct {
	X, Y, Z int
}

func (point Point3D) String() string {
	return fmt.Sprintf("{%d,%d,%d}", point.X, point.Y, point.Z)
}

func (point Point3D) Diff(from Point3D) float64 {
	x := point.X - from.X
	y := point.Y - from.Y
	z := point.Z - from.Z
	return math.Sqrt(float64(x*x) + float64(y*y) + float64(z*z))
}

func mainTwo() {
	pair2da := Pair[Point2D]{
		Point2D{X: 11, Y: 11},
		Point2D{X: 5, Y: 5},
	}
	pair2db := Pair[Point2D]{
		Point2D{X: 12, Y: 12},
		Point2D{X: 15, Y: 15},
	}
	closer := myFindCloser(pair2da, pair2db)
	fmt.Println(closer)

	pair3da := Pair[Point3D]{
		Point3D{X: 1, Y: 1, Z: 10},
		Point3D{X: 5, Y: 5, Z: 0},
	}
	pair3db := Pair[Point3D]{
		Point3D{X: 10, Y: 10, Z: 10},
		Point3D{X: 15, Y: 15, Z: 0},
	}
	closer2 := myFindCloser(pair3da, pair3db)
	fmt.Println(closer2)
}
