package main

import (
	"flag"
	"fmt"
	"log"
	"os"
	"os/user"
	"strings"
)

func scan(folder string) {
	fmt.Printf("Found folders:\n\n")

	repositories := recursiveScanFolder(folder)
	filePath := getDotFilePath()

	addNewSliceElementToFile(filePath, repositories)
	fmt.Printf("\n\nAdded Successfully")
}

func recursiveScanFolder(folder string) []string {
	return scanGitFolders(make([]string, 0), folder)
}

func scanGitFolders(folders []string, folder string) []string {
	// trim the last '/'
	folder = strings.TrimSuffix(folder, "/")

	dir, err := os.Open(folder)
	if err != nil {
		log.Fatal(err)
	}

	files, err := dir.ReadDir(-1)
	if err := dir.Close(); err != nil {
		log.Fatal(err)
	}

	for _, file := range files {
		if file.IsDir() {
			folders = processDirectory(folders, folder, file.Name())
		}
	}
	return folders
}

func processDirectory(folders []string, parentFolder, dirName string) []string {
	path := parentFolder + "/" + dirName

	if dirName == ".git" {
		path = strings.TrimSuffix(path, "/.git")
		fmt.Println(path)
		return append(folders, path)
	}
	if dirName == "vendor" || dirName == "node_modules" {
		return folders
	}
	return scanGitFolders(folders, path)
}

func getDotFilePath() string {
	usr, err := user.Current()
	if err != nil {
		log.Fatal(err)
	}

	dotFile := usr.HomeDir + "/.gogitlocalstats"
	return dotFile
}

func addNewSliceElementToFile(filePath string, newRepos []string) {
	existingRepos := parseFileLinesToSlice(filePath)
	repos := joinSlices(existingRepos, newRepos)
	dumpStringSliceToFile(repos, filePath)
}

func stats(email string) {
	print("stats")
}

func main() {
	var folder string
	var email string

	flag.StringVar(&folder, "add", "", "add a new folder to scan for Git repositories")
	flag.StringVar(&email, "email", "your@email.com", "the email to scan")
	flag.Parse()

	if folder != "" {
		scan(folder)
		return
	}
	stats(email)
}
