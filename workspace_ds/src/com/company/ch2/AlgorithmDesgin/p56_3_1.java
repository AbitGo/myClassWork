package com.company.ch2.AlgorithmDesgin;

import com.company.ch2.LinkTable.Node;
import com.company.ch2.SequenceTable.SequenceTable;

public class p56_3_1 {
    public static void main(String[] args) throws Exception {
        SequenceTable sequenceTable = new SequenceTable();
        for(int i = 0;i<10;i++){
            sequenceTable.insert(i,i);
        }
        sequenceTable.deserve();
        sequenceTable.display();

    }
}
