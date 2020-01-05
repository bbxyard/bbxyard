package dao

import (
	"go-iris-superstar/models"

	"github.com/go-xorm/xorm"
)

type SuperstarDao struct {
	engine *xorm.Engine
}

func NewSuperstarDao(engine *xorm.Engine) *SuperstarDao {
	return &SuperstarDao{engine: engine}
}

func (d *SuperstarDao) Get(id int) *models.StarInfo {
	inOutObj := &models.StarInfo{Id: id}
	ok, err := d.engine.Get(inOutObj)
	if ok && err == nil {
		return inOutObj
	} else {
		inOutObj.Id = 0
		return inOutObj
	}
}

func (d *SuperstarDao) GetAll() []models.StarInfo {
	datalist := make([]models.StarInfo, 0)
	err := d.engine.Desc("id").Find(&datalist)
	if err != nil {
		return datalist
	} else {
		return datalist
	}
}

func (d *SuperstarDao) Search(country string) []models.StarInfo {
	datalist := make([]models.StarInfo, 0)
	d.engine.Where("country=?", country).Desc("id").Find(&datalist)
	return datalist
}

func (d *SuperstarDao) Delete(id int) error {
	inData := &models.StarInfo{Id: id, SysStatus: 1}
	_, err := d.engine.Id(inOutData.Id).Delete(inData)
	return err
}

func (d *SuperstarDao) Update(patchInfo *models.StarInfo, columns []string) error {
	_, err := d.engine.Id(patchInfo.Id).MustCols(columns...).Update(patchInfo)
	return err
}

func (d *SuperstarDao) Create(newInfo *models.StarInfo) error {
	_, err := d.engine.Insert(newInfo)
	return err
}
