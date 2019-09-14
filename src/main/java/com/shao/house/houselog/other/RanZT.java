package com.shao.house.houselog.other;

public class RanZT {
    /**
     * 这里是我上一篇日记里面产生[0,1]随机数的算法
     * @param r 将变量的地址传进函数中，以便每次调用后更新随机种子的值，否则将得到完全一样的数据从而失去随机性
     * 这里r我们初始化为5
     * @return
     */
    static double seftRandom(double[] r){
        double base,u,v,p,temp1,temp2,temp3;
        //基数
        base = 256.0;
        //两个常数 uv;
        u = 17.0;
        v = 139.0;
        //计算总值
        temp1 = u*(r[0])+v;
        //计算商
        temp2 = (int)(temp1/base);
        //计算余数,1到base的余数
        temp3 = temp1 - temp2*base;
        //更新随机种子，为下一次使用
        r[0] = temp3;
        //随机数赋值 ，获取[0,1]的随机数
        p = r[0]/base;
        return p;
    }

    /**
     * 正态分布随机数生成法
     * @param u	正态分布的均值
     * @param t	正态分布的方差0
     * @param r	随机种子
     * @param n	正态分布公式的n
     * @return
     */
    static double randZT(double u,double t,double[]r,double n){
        int i;
        double total = 0.0;
        double result;
        for(i = 0;i<n;i++){
            //累加
            total += seftRandom(r);
        }
        //得到的随机数
        result = u+t*((total-n/2.0)/Math.sqrt(n/12));
        return result;
    }

    public static void main(String[] args){
        int i;
        double u,t,n;
        double[] r = {5.0};
        //初始化正态分布的均值和方差
        u = 0.0;
        t = 3.5;
        n = 12.0;
        System.out.println("产生十个正态分布的随机数：");
        //循环调用
        for(i = 0;i<100;i++){
            System.out.printf("%10.5f\n",randZT(u,t,r,n));
        }
        System.out.println();
    }
//    作者：Mesut_Ozil
//    来源：CSDN
//    原文：https://blog.csdn.net/xizai2012/article/details/8918708
//    版权声明：本文为博主原创文章，转载请附上博文链接！
}
