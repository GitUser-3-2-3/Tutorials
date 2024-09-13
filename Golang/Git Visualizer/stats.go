package main

func stats(email string) {
	commits := processRepositories(email)
	printCommitStats(commits)
}
