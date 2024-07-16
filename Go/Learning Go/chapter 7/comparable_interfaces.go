package main

import "fmt"

type Doubler interface {
	Double()
}

type DoubleInt int

func (double *DoubleInt) Double() {
	*double = *double * 2
}

type DoubleIntSlice []int

func (dISlice DoubleIntSlice) Double() {
	for i := range dISlice {
		dISlice[i] = dISlice[i] * 2
	}
}

func doublerCompare(d1, d2 Doubler) {
	fmt.Println(d1 == d2)
}

func calc() {
	var di1 DoubleInt = 10
	var di2 DoubleInt = 10

	var dis1 = DoubleIntSlice{1, 2, 3}
	var dis2 = DoubleIntSlice{1, 2, 3}

	doublerCompare(&dis1, &dis2)
	doublerCompare(&di1, &di2)
	doublerCompare(&di1, &dis2)
	doublerCompare(dis1, dis2)
}
