package main

import "errors"

type SimpleLogic struct {
	logger    Logger
	dataStore DataStore
}

func (logic SimpleLogic) SayHello(userId string) (string, error) {
	logic.logger.Log("in SayHello for " + userId)
	name, ok := logic.dataStore.UserNameForId(userId)

	if !ok {
		return "", errors.New("unknown user")
	}
	return "Hello, " + name, nil
}

func (logic SimpleLogic) SayGoodbye(userId string) (string, error) {
	logic.logger.Log("in SayGoodbye for " + userId)
	name, ok := logic.dataStore.UserNameForId(userId)

	if !ok {
		return "", errors.New("unknown user")
	}
	return "Goodbye, " + name, nil
}

func NewSimpleLogic(logger Logger, dataStore DataStore) SimpleLogic {
	return SimpleLogic{
		logger:    logger,
		dataStore: dataStore}
}
