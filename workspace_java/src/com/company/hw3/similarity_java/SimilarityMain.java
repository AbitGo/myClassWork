package com.company.hw3.similarity_java;

import java.util.ArrayList;
import java.util.Map;


public class SimilarityMain {

    float sim(ArrayList va, ArrayList vb) {
        // �������ά�Ȳ���ȣ����ܼ��㣬�����˳�
        if (va.size() != vb.size()) {
            return 0;
        }

        int size = va.size();
        float simVal = 0;

        //sim(va,vb) = (va * vb) / (|va| * |vb|)
        // ���� = va.get(0)*vb.get(0) + va.get(1)*vb.get(1) +...+ va.get(size - 1)*vb.get(size - 1)
        // ��ĸ = va��ģ * vb��ģ = sqrt((va.get(0))��ƽ�� + (va.get(1))��ƽ�� + ... + va.get(size - 1)��ƽ��) * sqrt((vb.get(0))��ƽ�� + (vb.get(1))��ƽ�� + ... + vb.get(size - 1)��ƽ��)
        float num = 0;// numerator����
        float den = 1;// denominator��ĸ
        float value_a = 0;
        float value_b = 0;

        for (int i = 0; i < size; i++) {
            num += ((float) va.get(i) * (float) vb.get(i));
            value_a += (float) va.get(i) * (float) va.get(i);
            value_b += (float) vb.get(i) * (float) vb.get(i);
        }
        den = (float) Math.sqrt(value_a * value_b);

        /*��ҵ��������*/


        simVal = num / den;
        return simVal;
    }

    public static void main(String[] args) {
        String item[] = {"��ƻ��", "���̵�", "�����Ӿ�", "����ë��", "�Խ���"};
        float a[] = {(float) 3.5, 5, 5, 5, 0};
        float b[] = {3, 5, 4, 5, 5};
        float c[] = {3, 3, 3, 3, 3};
        ArrayList vitem = new ArrayList();
        ArrayList va = new ArrayList();
        ArrayList vb = new ArrayList();
        ArrayList vc = new ArrayList();
        for (int i = 0; i < a.length; i++) {
            vitem.add(item[i]);
            va.add(new Float(a[i]));
            vb.add(new Float(b[i]));
            vc.add(new Float(b[i]));
        }
        System.out.print("��Ȥ");
        System.out.println(vitem);
        System.out.print("С��");
        System.out.println(va);
        System.out.print("С��");
        System.out.println(vb);

        System.out.print("Τ����");
        System.out.println(vc);

        SimilarityMain sim = new SimilarityMain();

        float simVal = sim.sim(va, vb);

        float simVal1 = sim.sim(va, vc);

        System.out.println("The sim value is:" + simVal);

        System.out.println("С����ҵ����ƶ�Ϊ:" + simVal1);
    }

}
