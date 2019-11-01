package com.company.ch7;

public class MergerSort {
    static int[] a = {3, 2, 1, 4, 5, 6, 7, 9, 0, 1};

    public static void merge(int[] r,int[] order,int h,int m,int t){
        //将r[i]-r[m]/r[m+1]-r[t]
        int i= h,k=h,j=m+1;
        while (i<=m && j<=t){
            if(r[i]<r[j]){
                order[k++]=r[i++];
            }else {
                order[k++]=r[j++];
            }
        }
        while (i<=m){
            order[k++]=r[i++];
        }
        while (j<=t){
            order[k++]=r[j++];
        }
    }

    public static void mergepass(int[] r,int[] order,int s,int n){
        //需要调用n/(2*s)回即可
        int p =0;
        while(p+2*s-1<=n-1){
            merge(r,order,p,p+s-1,p+2*s-1);
            p+=2*s;
        }
        //归并两个长度不等的有序表
        if(p+s-1<n-1){
            merge(r,order,p,p+s-1,n-1);
        }else{
            //将剩余的有序表复制到order中
            for(int i=p;i<=n-1;i++){
                order[i]=r[i];
            }
        }
    }
    public static void mergerSort(){

        int s = 1;
        int n = a.length;
        int[] temp = new int[n];
        while(s<n){
            mergepass(a,temp,s,n);
            s*=2;
            Display.display(temp);
            //将temp数组中各字序列在归并到r中
            mergepass(temp,a,s,n);
            s*=2;
        }
        Display.display(a);
    }

    public static void main(String[] args) {
        mergerSort();
        //Display.display(a);
    }
}
