package algorithmdesign.locationChoosing;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("************�������ѡַ************");

        System.out.println("�����뽨�������");
        System.out.println("���������");
        Tools t = new Tools();
        int n=t.n;//����
        int e=t.e;//��
        int max=Integer.MAX_VALUE;
        int i,j,w;
        for(i=1;i<=n;i++)   //��ʼ��ͼ�ı�
            for(j=1;j<=n;j++)
                if(i==j)
                    t.result[i][j]=0;
                else
                    t.result[i][j]=max;


        Scanner sc=new Scanner(System.in);
        for(int k=1;k<e+1;k++) {
            System.out.println("���������·����������λ");
            System.out.println("�����һ����λ,��Ӣ����ĸa��z��ʾ");
            String unit1=sc.next().toUpperCase();
            System.out.println("����ڶ�����λ,��Ӣ����ĸa��z��ʾ");
            String unit2=sc.next().toUpperCase();
            System.out.println("����������λ��ľ���");
            int unitPathLen=sc.nextInt();
            System.out.println("����������λ���Ƶ��");
            int frequency=sc.nextInt();
            int Wpart=unitPathLen*frequency;//���ݾ����Ƶ�ʼ���Ȩֵ
            String res=String.valueOf(Wpart);//Ϊ����Ĵ�����׼��
            String[] num=new String[3];//�洢��һ����λ �ڶ�����λ ��Ȩֵ
            num[0]=unit1;num[1]=unit2;num[2]=res;
            i=Change.changeToNum(num[0]);//����ĸת��Ϊ���� ������ͼ��,��һ����λ
            j=Change.changeToNum(num[1]);//�ڶ�����λ
            w=Change.changeToNum(num[2]);//������λ֮���Ȩֵ
            t.result[i][j]=w;  //
        }
        t.RecordW(t, n);    //���ø÷�����¼������λ���Ȩֵ
        t.add(t);         //������Ȩֵ���ȽϺ������СȨֵ�ĵ�λ
    }

}
