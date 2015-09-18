package test.java;

public class TestClass {
	public static void main(String[] args) throws ClassNotFoundException {
		TestClass class1 = new TestClass();
		Class<?> clazz = class1.getClass().getClassLoader().loadClass("test.java.TestGeneralPolices");
		System.out.println(clazz);
	}
}
