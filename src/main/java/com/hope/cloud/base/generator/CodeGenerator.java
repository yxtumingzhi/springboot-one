package com.hope.cloud.base.generator;


/**
 * 代码生成器
 *
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:15
 */
public class CodeGenerator {

	/**
	 * 代码生成的模块名
	 */
	public static String CODE_NAME = "应用管理";
	/**
	 * 代码所在服务名
	 */
	public static String SERVICE_NAME = "hope-cloud";
	/**
	 * 代码生成的包名
	 */
	public static String PACKAGE_NAME = "com.hope.cloud";
	/**
	 * 前端代码生成所属系统
	 */
	public static String SYSTEM_NAME = "saber";
	/**
	 * 代码后端生成的地址
	 */
	public static String packageDir = System.getProperty("user.dir") + "/user";
	/**
	 * 前端代码生成地址
	 */
	public static String PACKAGE_WEB_DIR = "/Users/chill/Workspaces/product/Saber";
	/**
	 * 需要去掉的表前缀
	 */
	public static String[] TABLE_PREFIX = {"blade_"};
	/**
	 * 需要生成的表名(两者只能取其一)
	 */
	public static String[] INCLUDE_TABLES = {"user"};
	/**
	 * 需要排除的表名(两者只能取其一)
	 */
	public static String[] EXCLUDE_TABLES = {};
	/**
	 * 是否包含基础业务字段
	 */
	public static Boolean HAS_SUPER_ENTITY = Boolean.FALSE;
	/**
	 * 是否包含包装器
	 */
	public static Boolean hasWrapper = Boolean.TRUE;
	/**
	 * 基础业务字段
	 */
	public static String[] SUPER_ENTITY_COLUMNS = {"create_time", "create_user", "update_time", "update_user", "status", "is_deleted"};


	/**
	 * RUN THIS
	 */
	public static void run() {
		BladeCodeGenerator generator = new BladeCodeGenerator();
		generator.setCodeName(CODE_NAME);
		generator.setServiceName(SERVICE_NAME);
		generator.setSystemName(SYSTEM_NAME);
		generator.setPackageName(PACKAGE_NAME);
		generator.setPackageWebDir(PACKAGE_WEB_DIR);
		generator.setTablePrefix(TABLE_PREFIX);
		generator.setIncludeTables(INCLUDE_TABLES);
		generator.setExcludeTables(EXCLUDE_TABLES);
		generator.setHasSuperEntity(HAS_SUPER_ENTITY);
		generator.setSuperEntityColumns(SUPER_ENTITY_COLUMNS);
		generator.setHasWrapper(hasWrapper);
		generator.setPackageDir(packageDir);
		generator.run();
	}

	public static void main(String[] args) {
		run();
	}
}
