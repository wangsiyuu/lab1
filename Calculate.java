package calculate;
//difference1

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Calculate {
	
	public static void main(String[] args){
		System.out.println("请输入表达式及相应的操作:");
		Scanner s = new Scanner(System.in);
		String Equa = null;//存表达式
		Calculate orz = new Calculate();
		while(true){
			String input = s.nextLine();
			int con = orz.control(input);//对输入进行判断控制
			switch(con){
			case 0:{
				System.exit(0);
				break;
			}
			case 1://Simplify
			{
				if(orz.standardS(input))
				{
					Equa = orz.convert(Equa);
					orz.simplify(input, Equa);
				}
				else
				{
					System.out.println("Invalid Input, Please Input Again!");
				}
				//if(Equa!=null) System.out.println(Equa);
				break;
			}
			case 2://Derivation
			{
				if(orz.standardD(input))
				{
					Equa = orz.convert(Equa);
					orz.derivation(input, Equa);
				}
				else
				{
					System.out.println("Invalid Input, Please Input Again!");
				}
				//if(Equa!=null) System.out.println(Equa);
				break;
			}
			case 3://Save as equation
			{
				if(orz.standardPM(input))
				{
					Equa = input;
					System.out.println(Equa);
				}
				else
				{
					System.out.println("Invalid Input, Please Input Again!");
				}
				break;
			}	
			}
		}
	}
	
	private int control(String st)//控制函数
	{
		if(st.equals("!exit"))
		{
			return 0;
		}
			
		else if(st.startsWith("!simplify"))
		{
			return 1;
		}
		else if(st.startsWith("!d/d"))
		{
			return 2;
		}
		else
		{
			return 3;
		}
	}
	
	private void simplify(String st, String pn)//化简函数
	{
		if(pn==null) 
		{
			return;
		}
		st = st.substring(9);
		if(st.length()==0)
		{
			System.out.println(pn);
			return;
		}
		st = st.substring(1);
		String[] num = st.split(" ");
		ArrayList<Character> list = new ArrayList<Character>();//判断化简命令中变量是否在多项式中
		for(int i = 0; i < pn.length(); i++)
		{
			char a = pn.charAt(i);
			if(Character.isAlphabetic(a)&&!list.contains(a))
			{
				list.add(a);
			}
		}
		/*for (int i = 0; i < list.size(); i++) {
		    System.out.println(list.get(i));
		}*/
		for(int i = 0; i < num.length; i++)
		{
			if(!list.contains(num[i].charAt(0)))
			{
				System.out.println("Some variavles do not exist!");
				continue;
			}
			else
			{
				//System.out.println(num[i].substring(0, 1));
				//System.out.println(num[i].substring(2, num[i].length()));
				pn = pn.replace(num[i].substring(0, 1), num[i].substring(2, num[i].length()));
			}//替换变量与数
		}
		//System.out.println(pn);
		String[] array1;
		String[] array2;//花间计算
		array1 = pn.split("\\+");
		for(int i = 0; i < array1.length; i++)
		{
			int m=1;
			String nullStr1="";
			//System.out.println(array1[i]);
			array2  = array1[i].split("\\*");
			for(int j = 0; j < array2.length; j++)
			{
				//System.out.println(array2[j]);
				if(Character.isDigit(array2[j].charAt(0)))
				{
					m = m*Integer.parseInt(array2[j]);
				}
				else
				{
					nullStr1+=array2[j] + "*";
				}
			}
			nullStr1+=m+"";
			//System.out.println(nullStr);
			array1[i]=nullStr1;
		}
		int n=0;
		String nullStr2="";
		for(int i = 0; i < array1.length; i++)
		{
			if(Character.isDigit(array1[i].charAt(0)))
			{
				n = n+Integer.parseInt(array1[i]);
			}
			else
			{
				if(array1[i].charAt(array1[i].length()-1)!='0') 
				{
					nullStr2+=array1[i] + "+";
				}
			}
		}
		if(n!=0)
		{
			nullStr2+=n+"";
		}
		else
		{
			nullStr2 = nullStr2.substring(0, nullStr2.length()-1);
		}
		pn = nullStr2;
		pn = pn.replace("*1", "");
		System.out.println(pn);
	}
	
	private void derivation(String st, String pn)//求导
	{
		if(pn==null) return;
		st = st.substring(4);
		if(st.length()==0){
			System.out.println(pn);
			return;
		}
		st = st.substring(1);
		ArrayList<Character> list1 = new ArrayList<Character>();//判断变量是否属于多项式
		for(int i = 0; i < pn.length(); i++){
			char a = pn.charAt(i);
			if(Character.isAlphabetic(a)&&!list1.contains(a)){
				list1.add(a);
			}
		}
		char var = st.charAt(0);
		if(!list1.contains(var))
		{
			System.out.println("Error, No Variable!");
			System.out.println(pn);
		}
		else
		{
			String[] array1 = pn.split("\\+");//求导
			//System.out.println(array1.length);
			for(int i = 0; i < array1.length; i++)
			{
				String[] array2 = array1[i].split("\\*");
				int count = 0;
				String temp = "";
				//System.out.println(array2.length);
				for(int j = 0;j < array2.length; j++)
				{
					if(array2[j].charAt(0)==var)
					{
						count++;
					}
					else
					{
						temp+=array2[j] + "*";
					}
				}
				for(int k = 0; k < count-1; k++)
				{
					temp+=var + "*";
				}
				temp+=count;
				//System.out.println(count);
				//System.out.println(temp);
				array1[i]=temp;
			}
			String temp2 = "";
			for(int i = 0; i < array1.length; i++)
			{
				if(!array1[i].endsWith("0"))
				{
					temp2+=array1[i]+"+";
				}
			}
			pn = temp2+"0";
			//System.out.println(pn);
			String[] array3;
			String[] array4;//化简
			array3 = pn.split("\\+");
			for(int i = 0; i < array3.length; i++)
			{
				int m=1;
				String nullStr1="";
				//System.out.println(array3[i]);
				array4  = array3[i].split("\\*");
				for(int j = 0; j < array4.length; j++)
				{
					//System.out.println(array4[j]);
					if(Character.isDigit(array4[j].charAt(0)))
					{
						m = m*Integer.parseInt(array4[j]);
					}
					else
					{
						nullStr1+=array4[j] + "*";
					}
				}
				nullStr1+=m+"";
				//System.out.println(nullStr);
				array3[i]=nullStr1;
			}
			int n=0;
			String nullStr2="";
			for(int i = 0; i < array3.length; i++)
			{
				if(Character.isDigit(array3[i].charAt(0)))
				{
					n = n+Integer.parseInt(array3[i]);
				}
				else
				{
					nullStr2+=array3[i] + "+";
				}
			}
			if(n!=0)
			{
				nullStr2+=n+"";
			}
			else
			{
				nullStr2 = nullStr2.substring(0, nullStr2.length()-1);
			}
			pn = nullStr2;
			pn = pn.replace("*1", "");
			System.out.println(pn);
		}
	}
	
	private boolean standardPM(String str)//输入多项式规则化判断
	{
		final String strRegex1 = "^[a-z0-9\\+\\*\\^]+$";
		Pattern pattern1 = Pattern.compile(strRegex1);
		Matcher matcher1 = pattern1.matcher(str);
		if(!matcher1.find()) return false;
		else if (str.startsWith("+")||str.startsWith("*")||str.endsWith("+")||str.endsWith("*")||str.startsWith("^")||str.endsWith("^"))
		{
			return false;
		}
		else{
			for(int i = 0; i < str.length()-1; i++)
			{
				char a = str.charAt(i);
				char b = str.charAt(i+1);
				if(Character.isDigit(a)&&Character.isAlphabetic(b)||Character.isDigit(b)&&Character.isAlphabetic(a))
				{
					return false;//如果字母前后是数字，错误
				}
				if((a=='+'||a=='*'||a=='^')&&(b=='+'||b=='*'||b=='^'))
				{
					return false;
				}
				if(Character.isAlphabetic(a)&&Character.isAlphabetic(b))
				{
					return false;
				}
				if(Character.isDigit(a)&&b=='^')
				{
					return false;
				}
				if(a=='^'&&Character.isAlphabetic(b))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean standardD(String str)//输入多项式形式规则判断，化简
	{
		if(str.length()!=6)
		{
			return false;
		}
		else if(!Character.isAlphabetic(str.charAt(str.length()-1)))
		{
			return false;
		}
		else if(str.charAt(str.length()-2)!=' ') 
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	private boolean standardS(String str)
	{
		str = str.substring(9);
		if(str.length()==0)
		{
			//System.out.println(pn);
			return true;
		}
		str = str.substring(1);
		final String strRegex1 = "^[a-z0-9\\=\\ ]+$";
		Pattern pattern1 = Pattern.compile(strRegex1);
		Matcher matcher1 = pattern1.matcher(str);
		if(!matcher1.find())
		{
			return false;
		}
		String[] array = str.split(" ");
		for(int i = 0; i < array.length; i++)
		{
			//System.out.println(array[i]);
			if(array[i].length()<3)
			{
				return false;
			}
			if(!Character.isAlphabetic(array[i].charAt(0))||array[i].charAt(1)!='=')
			{
				return false;
			}
			for(int j = 2; j < array[i].length(); j++)
			{
				if(!Character.isDigit(array[i].charAt(j))) 
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private String convert(String str)//形式转换
	{
		if(str==null)
		{
			System.out.println("Please Input A Polynominal First!");
			return str;
		}
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i)=='^')
			{
				int num = Integer.parseInt(String.valueOf(str.charAt(i+1)));
				String nullStr = str.substring(0, i);
				for(int j = 0; j < num - 1 ; j++)
				{
					nullStr = nullStr + "*" + str.charAt(i-1);
				}
				//System.out.println(nullStr);
				str = nullStr + str.substring(i+2, str.length());
				//System.out.println(st);
			}
		}
		//System.out.println(st);
		return str;
	}
}
