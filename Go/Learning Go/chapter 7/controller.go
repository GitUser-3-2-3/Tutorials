package main

import "net/http"

type Logic interface {
	SayHello(userId string) (string, error)
}

type Controller struct {
	logger Logger
	logic  Logic
}

func (ctrl Controller) SayHello(writer http.ResponseWriter, req *http.Request) {
	ctrl.logger.Log("in SayHello")

	userId := req.URL.Query().Get("user_id")
	message, err := ctrl.logic.SayHello(userId)

	if err != nil {
		writer.WriteHeader(http.StatusBadRequest)
		writer.Write([]byte(err.Error()))
		return
	}
	writer.Write([]byte(message))
}

func NewController(logger Logger, logic Logic) Controller {
	return Controller{
		logger: logger,
		logic:  logic,
	}
}

func ctrlMain() {
	logger := LoggerAdapter(LogOutput)
	dataStore := NewSimpleDataStore()
	logic := NewSimpleLogic(logger, dataStore)
	controller := NewController(logger, logic)

	http.HandleFunc("/hello", controller.SayHello)
	err := http.ListenAndServe("localhost:8080", nil)
	if err != nil {
		return
	}
}
