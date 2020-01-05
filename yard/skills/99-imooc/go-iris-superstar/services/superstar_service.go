package services

import (
	"go-iris-superstar/dao"
	"go-iris-superstar/datasource"
	"go-iris-superstar/models"
)

type SuperstarService interface {
	GetAll() []models.StarInfo
	Search(country string) []models.StarInfo
	Get(id int) *models.StarInfo
	Delete(id int) error
	Update(patchInfo *models.StarInfo, columns []string) error
	Create(newInfo *models.StarInfo) error
}

func NewSuperstarService() SuperstarService {
	masterDao := dao.NewSuperstarDao(datasource.InstanceMaster())
	return &superstarService{
		dao: masterDao,
	}
}

type superstarService struct {
	dao *dao.SuperstarDao
}

func (s *superstarService) GetAll() []models.StarInfo {
	return s.dao.GetAll()
}

func (s *superstarService) Search(country string) []models.StarInfo {
	return s.dao.Search(country)
}

func (s *superstarService) Get(id int) *models.StarInfo {
	return s.dao.Get(id)
}

func (s *superstarService) Delete(id int) error {
	return s.dao.Delete(id)
}

func (s *superstarService) Update(patchInfo *models.StarInfo, columns []string) error {
	return s.dao.Update(patchInfo, columns)
}

func (s *superstarService) Create(newInfo *models.StarInfo) error {
	return s.dao.Create(newInfo)
}
