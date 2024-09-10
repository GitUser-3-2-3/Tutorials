package main

import (
	"encoding/json"
	"fmt"
)

const Version = "1.0.1"

type Address struct {
	City    string
	State   string
	Country string
	PinCode json.Number
}

type User struct {
	Name    string
	Age     json.Number
	Contact string
	Company string
	Address Address
}

func main() {
	directory := "./"

	database, err := New(directory, nil)
	if err != nil {
		fmt.Println("Error ", err)
	}

	employees := []User{
		{
			Name:    "John",
			Age:     "23",
			Contact: "9335582338",
			Company: "Amazon",
			Address: Address{
				City:    "Bangalore",
				State:   "Karnataka",
				Country: "India",
				PinCode: "273008",
			},
		},
		{
			Name:    "Alice",
			Age:     "30",
			Contact: "9123456789",
			Company: "Google",
			Address: Address{
				City:    "Mountain View",
				State:   "California",
				Country: "USA",
				PinCode: "94043",
			},
		},
		{
			Name:    "Bob",
			Age:     "28",
			Contact: "9876543210",
			Company: "Microsoft",
			Address: Address{
				City:    "Redmond",
				State:   "Washington",
				Country: "USA",
				PinCode: "98052",
			},
		},
		{
			Name:    "Charlie",
			Age:     "35",
			Contact: "9988776655",
			Company: "Facebook",
			Address: Address{
				City:    "Menlo Park",
				State:   "California",
				Country: "USA",
				PinCode: "94025",
			},
		},
		{
			Name:    "David",
			Age:     "40",
			Contact: "9112233445",
			Company: "Apple",
			Address: Address{
				City:    "Cupertino",
				State:   "California",
				Country: "USA",
				PinCode: "95014",
			},
		},
		{
			Name:    "Eve",
			Age:     "27",
			Contact: "9223344556",
			Company: "Netflix",
			Address: Address{
				City:    "Los Gatos",
				State:   "California",
				Country: "USA",
				PinCode: "95032",
			},
		},
		{
			Name:    "Frank",
			Age:     "32",
			Contact: "9334455667",
			Company: "Twitter",
			Address: Address{
				City:    "San Francisco",
				State:   "California",
				Country: "USA",
				PinCode: "94103",
			},
		},
		{
			Name:    "Grace",
			Age:     "29",
			Contact: "9445566778",
			Company: "LinkedIn",
			Address: Address{
				City:    "Sunnyvale",
				State:   "California",
				Country: "USA",
				PinCode: "94085",
			},
		},
		{
			Name:    "Hank",
			Age:     "26",
			Contact: "9556677889",
			Company: "Tesla",
			Address: Address{
				City:    "Palo Alto",
				State:   "California",
				Country: "USA",
				PinCode: "94304",
			},
		},
		{
			Name:    "Ivy",
			Age:     "31",
			Contact: "9667788990",
			Company: "Uber",
			Address: Address{
				City:    "San Francisco",
				State:   "California",
				Country: "USA",
				PinCode: "94105",
			},
		},
	}

	for _, value := range employees {
		database.Write("users", value.Name, User{
			Name:    value.Name,
			Age:     value.Age,
			Contact: value.Contact,
			Company: value.Company,
			Address: value.Address,
		})
	}

	records, err := database.ReadAll("users")
	if err != nil {
		fmt.Println("Error ", err)
	}
	fmt.Println(records)

	var allUsers []User

	for _, record := range records {
		employeeFound := User{}
		if err := json.Unmarshal([]byte(record), &employeeFound); err != nil {
			fmt.Println("Error ", err)
		}
		allUsers = append(allUsers, employeeFound)
	}
	fmt.Println(allUsers)

	/*
		if err := database.Delete("user", "john"); err != nil {
			fmt.Println("Error ", err)
		}

		if err := database.Delete("user", ""); err != nil {
			fmt.Println("Error ", err)
		}
	*/
}
