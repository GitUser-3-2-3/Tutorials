package main

type DataStore interface {
	UserNameForId(userId string) (string, bool)
}

type Logger interface {
	Log(message string)
}

type LoggerAdapter func(message string)

func (logger LoggerAdapter) Log(message string) {
	logger(message)
}


