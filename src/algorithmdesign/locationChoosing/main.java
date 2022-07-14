package algorithmdesign.locationChoosing;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("************超市最佳选址************");

        System.out.println("请输入建筑物个数");
        System.out.println("请输入边数");
        Tools t = new Tools();
        int n=t.n;//顶点
        int e=t.e;//边
        int max=Integer.MAX_VALUE;
        int i,j,w;
        for(i=1;i<=n;i++)   //初始化图的边
            for(j=1;j<=n;j++)
                if(i==j)
                    t.result[i][j]=0;
                else
                    t.result[i][j]=max;


        Scanner sc=new Scanner(System.in);
        for(int k=1;k<e+1;k++) {
            System.out.println("请输入存在路径的两个单位");
            System.out.println("输入第一个单位,用英文字母a到z表示");
            String unit1=sc.next().toUpperCase();
            System.out.println("输入第二个单位,用英文字母a到z表示");
            String unit2=sc.next().toUpperCase();
            System.out.println("输入两个单位间的距离");
            int unitPathLen=sc.nextInt();
            System.out.println("输入两个单位间的频率");
            int frequency=sc.nextInt();
            int Wpart=unitPathLen*frequency;//根据距离和频率计算权值
            String res=String.valueOf(Wpart);//为下面的储存做准备
            String[] num=new String[3];//存储第一个单位 第二个单位 和权值
            num[0]=unit1;num[1]=unit2;num[2]=res;
            i=Change.changeToNum(num[0]);//将字母转换为数字 保存在图中,第一个单位
            j=Change.changeToNum(num[1]);//第二个单位
            w=Change.changeToNum(num[2]);//两个单位之间的权值
            t.result[i][j]=w;  //
        }
        t.RecordW(t, n);    //调用该方法记录两个单位间的权值
        t.add(t);         //计算总权值并比较后给出最小权值的单位
    }

}
