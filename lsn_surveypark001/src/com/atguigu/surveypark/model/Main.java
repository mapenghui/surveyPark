package com.atguigu.surveypark.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			int n = scan.nextInt();
			String s = scan.nextLine();
			String[] c = s.split(s, ',');
		}
	} 
	public static void hongbao(String[] c) {
		String[] s = c;
		List list = new ArrayList(){};
		for(int i=0; i<c.length; i+=2) {
			if(i==0 || i == c.length-1) {
				
			}
			list.add(s[i]);
			s[i] = "-1";
		}
	}
}
