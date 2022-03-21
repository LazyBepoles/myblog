package cn.liang.service;

import cn.liang.pojo.Type;

import java.util.List;

public interface TypesService {

    List<Type> queryAllTypes();

    List<Type> queryTypesByName(String name);

    Type queryTypeById(int id);

    int addType(Type type);

    int updateType(Type type);

    int deleteType(int id);

    Type queryByName(String name);

    List<Type> queryTypesTop();
}
