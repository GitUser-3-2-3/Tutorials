package main

import (
	"fmt"
	"sync"
	"time"
)

var mutex = sync.Mutex{}
var waitGroup = sync.WaitGroup{}
var dbData = []string{"id1", "id2", "id3", "id4", "id5"}
var results []string

func main() {
	t0 := time.Now()
	for i := 0; i < len(dbData); i++ {
		waitGroup.Add(1)
		go dbCall(i)
	}
	waitGroup.Wait()
	fmt.Printf("\nTotal execution time: %v\n", time.Since(t0))
	fmt.Printf("\nThe results are: %v", results)
}

func dbCall(index int) {
	// simulate dbCall delay
	var delay float32 = 2000
	time.Sleep(time.Duration(delay) * time.Millisecond)

	mutex.Lock()
	fmt.Println("The result from the database is: ", dbData[index])
	results = append(results, dbData[index])
	mutex.Unlock()

	waitGroup.Done()
}
