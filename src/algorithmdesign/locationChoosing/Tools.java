package algorithmdesign.locationChoosing;

import java.util.Scanner;

public class Tools {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();//����
    int e=sc.nextInt();//��
    int result[][]=new int[n+1][n+1];//����ͼ
    int a[][]=new int[n+1][n+1];//������Ȩֵ

    /**
     * ���ƹ�ʽ��d[i][j]=min(d[i][j],d[j][k]+d[k][j])
     * @param tools
     * @param n
     */
    public void RecordW(Tools tools, int n) {
        //��¼������λ֮���Ȩֵ
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                tools.a[i][j] = tools.result[i][j]; //���������Ȩֵ �洢����һ����ά����
            }

//        for(int k=1;k<=n;k++)
//            for (int i = 1; i <= n; i++)
//                for (int j = 1; j <= n; j++) {
//                    tools.a[i][j] = Math.min(tools.a[i][j],tools.a[i][k]+tools.a[k][j]);
//                }



        for(int i=1;i<=n;i++)           //��ȡͼ�е����ݴ�ӡ����
            for (int j = 1; j <= n; j++) {
                if (i != j)
                    System.out.println(Change.changeToStr(i) + "��" + Change.changeToStr(j) + "��ȨֵΪ" + tools.a[i][j]);
            }
    }



    //����ÿһ����λ��������λ����Ȩֵ���Ƚ��ĸ���С���������ѡַ��
    public void add(Tools tools) {
        int[] sum=new int[n+1];   //����һά����洢ÿ����λ����Ȩֵ
        for(int i=0;i<n+1;i++)    //��ʼ��һλ���飬��Ϊ0
            sum[i]=0;
        for(int i=1;i<=n;i++) {   //��ʼ����ÿ����λ����Ȩֵ
            for(int j=1;j<=n;j++) {
                if(i!=j) {
                    sum[i]=sum[i]+ tools.a[i][j];//tools.a[i][j]����ʾÿ����λ��������λ��Ȩֵ
                }
            }
            System.out.println();
            if(sum[i]>10000) {           //�ж���������Ƿ�Ϊͨ·
                System.out.print(Change.changeToStr(i)+"�޷����������");
            }else {
                System.out.print(Change.changeToStr(i)+"���������Ȩֵ�ܺ�Ϊ"+sum[i]);
            }

            System.out.println();
        }

        //�������Ȩֵ��С�ĵ�λ
        int address=1;//�������λ��
        for(int i=2;i< sum.length;i++)    //������Сֵ
            if(sum[1]>sum[i]) {
                sum[1]=sum[i];
                address=i;
            }
        System.out.println("������СȨֵ�ܺ�Ϊ"+sum[1]+","+"ѧԺ���е����ѡַΪ����"+Change.changeToStr(address));
        System.out.println("(Ȩֵ�ܺ�ԽС����ô�����Խ��)");
    }


}
