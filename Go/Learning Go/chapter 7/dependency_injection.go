package main

import "fmt"

func LogOutput(message string) {
	fmt.Println(message)
}

type SimpleDataStore struct {
	userData map[string]string
}

func (dataStore SimpleDataStore) UserNameForId(userId string) (string, bool) {
	name, ok := dataStore.userData[userId]
	return name, ok
}

func NewSimpleDataStore() SimpleDataStore {
	return SimpleDataStore{userData: map[string]string{
		"1": "Fred",
		"2": "Mary",
		"3": "Pat",
	}}
}
