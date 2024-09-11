package main

import (
	"encoding/json"
	"fmt"
	"github.com/jcelliott/lumber"
	"os"
	"path/filepath"
	"sync"
)

const Version = "1.0.0"

type (
	Logger interface {
		Fatal(string, ...interface{})
		Error(string, ...interface{})
		Warn(string, ...interface{})
		Info(string, ...interface{})
		Debug(string, ...interface{})
		Trace(string, ...interface{})
	}

	Driver struct {
		mutex   sync.Mutex
		mutexes map[string]*sync.Mutex
		dir     string
		log     Logger
	}
)

type Address struct {
	City    string
	State   string
	Country string
	PinCode json.Number
}

type Options struct {
	Logger
}

type User struct {
	Name    string
	Age     json.Number
	Contact string
	Company string
	Address Address
}

func New(dir string, options *Options) (*Driver, error) {
	dir = filepath.Clean(dir)

	opts := Options{}

	if options != nil {
		opts = *options
	}

	if opts.Logger == nil {
		opts.Logger = lumber.NewConsoleLogger(lumber.INFO)
	}

	driver := Driver{
		mutexes: make(map[string]*sync.Mutex),
		dir:     dir,
		log:     opts.Logger,
	}

	if _, err := os.Stat(dir); err != nil {
		opts.Logger.Debug("Using '%s' (database already exists)\n", dir)
		return &driver, nil
	}

	opts.Logger.Debug("Creating the database at '%s'...\n", dir)
	return &driver, os.MkdirAll(dir, 0755)
}

func (driver *Driver) Write(collection, resource string, values interface{}) error {
	if collection == "" {
		return fmt.Errorf("missing collection - no place to save record")
	}
	if resource == "" {
		return fmt.Errorf("missing resource - unable to save record (no name)")
	}

	mutex := driver.getOrCreateMutex(collection)
	mutex.Lock()
	defer mutex.Unlock()

	dir := filepath.Join(driver.dir, collection)
	finalPath := filepath.Join(dir, resource+".json")
	tempPath := finalPath + ".tmp"

	if err := os.MkdirAll(dir, 0755); err != nil {
		return err
	}

	userData, err := json.MarshalIndent(values, "", "\t")
	if err != nil {
		return err
	}

	userData = append(userData, byte('\n'))
	if err := os.WriteFile(tempPath, userData, 0644); err != nil {
		return err
	}

	return os.Rename(tempPath, finalPath)
}

func (driver *Driver) Read(collection, resource string, values interface{}) error {
	if collection == "" {
		return fmt.Errorf("missing collection - unable to read")
	}
	if resource == "" {
		return fmt.Errorf("missing resource - unable to save record (no name)")
	}

	record := filepath.Join(driver.dir, collection, resource)
	if _, err := stat(record); err != nil {
		return err
	}

	userData, err := os.ReadFile(record + ".json")
	if err != nil {
		return err
	}

	return json.Unmarshal(userData, values)
}

func (driver *Driver) ReadAll(collection string) ([]string, error) {

	if collection == "" {
		return nil, fmt.Errorf("missing collection - unable to read")
	}

	dir := filepath.Join(driver.dir, collection)
	if _, err := stat(dir); err != nil {
		return nil, err
	}

	files, _ := os.ReadDir(dir)
	var records []string

	for _, file := range files {
		data, err := os.ReadFile(filepath.Join(dir, file.Name()))
		if err != nil {
			return nil, err
		}

		records = append(records, string(data))
	}
	return records, nil
}

func (driver *Driver) getOrCreateMutex(collection string) *sync.Mutex {
	driver.mutex.Lock()
	defer driver.mutex.Unlock()

	mutex, ok := driver.mutexes[collection]
	if !ok {
		mutex = &sync.Mutex{}
		driver.mutexes[collection] = mutex
	}

	return mutex
}

func stat(path string) (file os.FileInfo, err error) {
	if file, err = os.Stat(path); os.IsNotExist(err) {
		file, err = os.Stat(path + ".json")
	}
	return
}

func main() {
	dir := "./"

	database, err := New(dir, nil)
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
		err := database.Write("users", value.Name, User{
			Name:    value.Name,
			Age:     value.Age,
			Contact: value.Contact,
			Company: value.Company,
			Address: value.Address,
		})
		if err != nil {
			_ = fmt.Errorf("error occured while inside 'Write'")
		}
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
