package com.ganster.cms.core.base.impl;

import com.ganster.cms.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {
    @Autowired
    public Mapper mapper;

    @Override
    public long countByExample(Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("countByExample", example.getClass());
            Object result = method.invoke(mapper, example);
            return Long.parseLong(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public int deleteByExample(Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("deleteByExample", example.getClass());
            Object result = method.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = method.invoke(mapper, id);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(Record record) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("insert", record.getClass());
            Object result = method.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertSelective(Record record) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("insertSelective", record.getClass());
            Object result = method.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> selectByExampleWithBLOBs(Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs", example.getClass());
            Object result = method.invoke(mapper, example);
            return (List<Record>)result;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Record> selectByExample(Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            Object result = method.invoke(mapper, example);
            return (List<Record>)result;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Record selectByPrimaryKey(Integer id) {
        try {
            if (mapper==null)
                System.out.println("MMMMMMPPPPPPPERRERERERERREERRERERERERE R FHGDFG SFDHF SJFH JA Y");
            Method method = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", Integer.class);
            Object result = method.invoke(mapper, id);
            return (Record)result;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateByExampleSelective(Record record, Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("updateByExampleSelective", example.getClass());
            Object result = method.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByExampleWithBLOBs(Record record, Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("updateByExampleWithBLOBs", example.getClass());
            Object result = method.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByExample(Record record, Example example) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("updateByExample", record.getClass());
            Object result = method.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKeySelective", record.getClass());
            Object result = method.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Record record) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKeyWithBLOBs", record.getClass());
            Object result = method.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        try {
            Method method = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", record.getClass());
            Object result = method.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
