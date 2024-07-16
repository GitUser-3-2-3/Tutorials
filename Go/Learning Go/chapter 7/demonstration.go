package main

type Juhi interface {
	BuyClothes()
}

type JobX interface {
	JavaBackend()
	GoCloud()
	RustSystem()
}

type ApplicantOne struct {
	Name string
	Age int
}

type ApplicantTwo struct {
	 Name string
	 Age int
}

func (one ApplicantOne) JavaBackend() {
}

func (one ApplicantOne) RustSystem() {
}

func (one ApplicantOne) GoCloud() {
}

func (one ApplicantOne) BuyClothes() {

}

func (one ApplicantOne) SometimeCodes() {

}

func (one ApplicantOne) CooksGoodFood() {

}