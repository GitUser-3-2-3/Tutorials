package main

import "fmt"

type gasEngine struct {
	mpg     uint8
	gallons uint8
}

type electricEngine struct {
	mpkwh uint8
	kwh   uint8
}

type Engine interface {
	milesLeft() uint8
}

func (engine *gasEngine) milesLeft() uint8 {
	return engine.gallons * engine.mpg
}

func (engine *electricEngine) milesLeft() uint8 {
	return engine.kwh * engine.mpkwh
}

func canMakeIt(engine Engine, miles uint8) string {
	if miles > engine.milesLeft() {
		return "Sorry you don't have enough miles left"
	} else {
		return "You can make the trip"
	}
}

func main() {
	myEngine := gasEngine{
		mpg:     25,
		gallons: 11,
	}
	fmt.Println(myEngine)

	myElectricEngine := electricEngine{
		mpkwh: 35,
		kwh:   10,
	}
	fmt.Println(myElectricEngine)

	// Anonymous struct -> not reusable
	myEngine2 := struct {
		mpg     uint8
		gallons uint8
	}{25, 12}
	fmt.Println(myEngine2)

	fmt.Printf("Total miles left in the tank %v\n", myEngine.milesLeft())
	fmt.Printf("Total miles left in the tank %v\n", myElectricEngine.milesLeft())

	forElectric := canMakeIt(&myElectricEngine, 42)
	forGas := canMakeIt(&myEngine, 42)
	fmt.Println(forElectric)
	fmt.Println(forGas)
}
