package main

import (
	"errors"
)

type Integer interface {
	int | int8 | int16 | int32 | int64 |
		uint | uint8 | uint16 | uint32 | uint64 | uintptr
}

func divAndRemainder[T Integer](num, denom T) (T, T, error) {
	if denom == 0 {
		return 0, 0, errors.New("cannot divide by zero")
	}
	return num / denom, num % denom, nil
}

type Ordered interface {
	~int | ~int8 | ~int16 | ~int32 | ~int64 |
		~uint | ~uint8 | ~uint16 | ~uint32 | ~uint64 | ~uintptr | ~float32 | ~float64 |
		~string
}

type PrintableInt interface {
	~int
	String() string
}

type ImpossiblePrintableInt interface {
	int
	String() string
}

type ImpossibleStruct[T ImpossiblePrintableInt] struct {
	val T
}

type myInt int
