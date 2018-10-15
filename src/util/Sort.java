/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.StaffMyNumber;
import java.util.ArrayList;

/**
 *
 * @author 佐藤孝史
 */
public class Sort {
    
    // ソートのモードを指定
    public static enum SortMode {
        SELECT(1),
        BUCKET(2),
        BUBBLE(3),
        QUICK(4),
        ;
        
        private final int id;

        private SortMode(final int id) {
            this.id = id;
        }

        public int getInt() {
            return this.id;
        }
        
        public static SortMode getEnumName(int id) {
            for(SortMode v : values())
            {
                if(v.getInt() == id)
                {
                    return v;
                }

            }
            throw new IllegalArgumentException("undefined : " + id);
        }
    }
    
    public static void sortAge(ArrayList<StaffMyNumber> list, int mode) {
        
        // ソートの種類を選択
        switch (SortMode.getEnumName(mode)) {
            // 選択ソート
            case SELECT:
                selectSortAge(list);
                break;
            // バケットソート
            case BUCKET:
                bucketSortAge(list);
                break;
            // バブルソート
            case BUBBLE:
                bubbleSortAge(list);
                break;
            // バブルソート
            case QUICK:
                quickSortAge(list, 0, list.size()-1);
                break;
            default:
                System.out.println("そんなモードはありません");
                break;
        }
    }
    
    private static void selectSortAge(ArrayList<StaffMyNumber> list) {
        
        System.out.println("*選択ソート*");

        for(int i = 0; i < list.size(); i++ ){
            int index = i;
            for(int j = i + 1; j < list.size(); j++){
                if(list.get(j).getAge() < list.get(index).getAge()){
                    index = j;
                }
            }
            if(i != index){
                StaffMyNumber tmp = list.get(index);
                list.set(index, list.get(i));
                list.set(i,tmp);
            }
        }
    }
    
    private static void bucketSortAge(ArrayList<StaffMyNumber> list) {
        
        System.out.println("*バケットソート*");
        
        ArrayList<ArrayList<StaffMyNumber>> bucket = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            bucket.add(new ArrayList<>());
        }
        
        for (int i = 0; i < list.size(); i++) {
            bucket.get(list.get(i).getAge()).add(list.get(i));
        }
        
        int j = 0;
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i) != null) {
                while (bucket.get(i).size() > 0) {
                    StaffMyNumber tmp = (StaffMyNumber)bucket.get(i).get(0);
                    bucket.get(i).remove(0);
                    list.set(j, tmp);
                    j++;
                }
            }
        }
        
        bucket = null;
    }
    
    private static void bubbleSortAge(ArrayList<StaffMyNumber> list) {
        
        System.out.println("*バブルソート*");
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size()-1; j > i; j--) {
                System.out.println(list.get(j).getAge() + "," + list.get(j-1).getAge());
                if (list.get(j).getAge() < list.get(j-1).getAge()) {
                    StaffMyNumber tmp = list.get(j);
                    list.set(j, list.get(j-1));
                    list.set(j-1,tmp);
                }
            }
            System.out.println(list.get(i).getAge());
        }
    }
    
    private static void quickSortAge(ArrayList<StaffMyNumber> list, int left, int right) {
        
        System.out.println("*クイックソート*");
        
        if (left >= right) {
            return;
        }
        
        // ピボット値を決める
        int pivot = setMedian(list.get(left).getAge(), list.get(left + (right - left) / 2).getAge(), list.get(right).getAge());
        
        int l = left;
        int r = right;
        
        while (true) {
            // ピボットより小さいデータのインデックスを見つける
            while (Integer.valueOf(list.get(l).getAge()).compareTo(pivot) < 0) {
                l++;
            }
            // ピボットより大きいデータのインデックスを見つける
            while (Integer.valueOf(list.get(r).getAge()).compareTo(pivot) > 0) {
                r--;
            }
            // 見つかったインデックスの大小が逆転していれば、終了
            if (l >= r) {
                break;
            }
            
            StaffMyNumber tmp = list.get(l);
            list.set(l, list.get(r));
            list.set(r,tmp);
            
            l++;
            r--;
        }
        
        quickSortAge(list, left, l-1);
        quickSortAge(list, r+1, right);
    }
    
    private static int setMedian(Integer x, Integer y, Integer z) {
        
        if (x.compareTo(y) < 0) {
            return x.compareTo(z) < 0 ? (y.compareTo(z) < 0 ? y : z) : x;
        } else {
            return y.compareTo(z) < 0 ? (x.compareTo(z) < 0 ? x : z) : y;
        }
    }
}
