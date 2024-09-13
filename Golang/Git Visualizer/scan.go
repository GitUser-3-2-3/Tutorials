package main

import (
	"bufio"
	"fmt"
	"io"
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

func parseFileLinesToSlice(filePath string) []string {
	file := openFile(filePath)
	defer func() {
		if err := file.Close(); err != nil {
			log.Fatal(err)
		}
	}()

	scanner := bufio.Scanner{}
	var lines []string
	for scanner.Scan() {
		lines = append(lines, scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		if err != io.EOF {
			panic(err)
		}
	}
	return lines
}

func openFile(filePath string) *os.File {
	file, err := os.OpenFile(filePath, os.O_APPEND|os.O_WRONLY, 0755)

	if err != nil {
		if os.IsNotExist(err) {
			if _, err := os.Create(filePath); err != nil {
				panic(err)
			}
		} else {
			panic(err)
		}
	}
	return file
}

func joinSlices(new []string, existing []string) []string {
	for _, val := range new {
		if !sliceContains(existing, val) {
			existing = append(existing, val)
		}
	}
	return existing
}

func sliceContains(slice []string, value string) bool {
	for _, v := range slice {
		if v == value {
			return true
		}
	}
	return false
}

func dumpStringSliceToFile(repos []string, filePath string) {
	content := strings.Join(repos, "\n")
	if err := os.WriteFile(filePath, []byte(content), 0755); err != nil {
		return
	}
}
