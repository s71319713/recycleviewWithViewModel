package com.example.recycleviewwithviewmodel.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    MutableLiveData<Integer> score ;
    MutableLiveData<ArrayList<String>> stringList;

    public MyViewModel(){
        if(stringList==null){
            stringList = new MutableLiveData<>();
//            ArrayList<String> aaa =
//            aaa.add("初始值");
            stringList.setValue(new ArrayList<String>());
        }
    }

    public MutableLiveData<Integer> getScore() {
        if(score==null){
         score = new MutableLiveData<>();
         score.setValue(0);
        }
        return score;
    }

    public MutableLiveData<ArrayList<String>> getStringList() {
        return stringList;
    }

    public  void addString(String str){
        ArrayList<String> strings= stringList.getValue();
        strings.add(str);
        stringList.setValue(strings);
    }

    public void addScore(Integer num,Repository repository){
        score.setValue(score.getValue()+num);
//        addString("我愛你"+score.getValue().toString());
        repository.insert("我愛你"+score.getValue().toString());
    }
}
