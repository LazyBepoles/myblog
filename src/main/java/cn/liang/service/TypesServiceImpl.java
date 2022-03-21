package cn.liang.service;

import cn.liang.dao.TypesMapper;
import cn.liang.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypesServiceImpl implements TypesService{

    @Autowired
    private TypesMapper typesMapper;

    @Override
    public List<Type> queryAllTypes() {
        List<Type> listType= typesMapper.queryAllTypes();
        return listType;
    }

    @Transactional
    @Override
    public int addType(Type type) {
        int result = typesMapper.addType(type);
        return result;
    }

    @Override
    public Type queryTypeById(int id) {
        Type queryResult = typesMapper.queryTypeById(id);
        return queryResult;
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        int result = typesMapper.updateType(type);
        return result;
    }

    @Transactional
    @Override
    public int deleteType(int id) {
        int result = typesMapper.deleteType(id);
        return result;
    }

    @Override
    public Type queryByName(String name) {
        Type queryResult = typesMapper.queryByName(name);
        return queryResult;
    }

    @Override
    public List<Type> queryTypesTop() {
        List<Type> typeList = typesMapper.queryTypesTop();
        return typeList;
    }

    @Override
    public List<Type> queryTypesByName(String name) {
        List<Type> typeList = typesMapper.queryTypesByName(name);
        return typeList;
    }
}
