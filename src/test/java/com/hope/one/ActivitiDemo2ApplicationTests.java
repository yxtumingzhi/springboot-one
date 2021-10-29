//package com.hope.one;
//
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.history.HistoricTaskInstance;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//public class ActivitiDemo2ApplicationTests {
//
//    @Autowired
//    RepositoryService repositoryService;
//
//    @Autowired
//    RuntimeService runtimeService;
//
//    @Autowired
//    TaskService taskService;
//
//    @Autowired
//    HistoryService historyService;
//
//    @Test
//    void contextLoads() {
//        System.out.println("Number of process definitions : "
//                + repositoryService.createProcessDefinitionQuery().count());
//        System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//        runtimeService.startProcessInstanceByKey("oneTaskProcess");
//        System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
//    }
//
//
//    @Test
//    void testProcess() {
//        //张三开启一个请假流程
//        String user = "张三";
//        String approve = "领导李四";
//        startLeaveProcess(user,"leave");
//
//
//        //张三查询自己流程
//        queryLeaveProcessING(user);
//
//
////        提交给领导李四审核
//        String taskId = "34f8958d-0d07-11ea-b319-9c5c8e7034f6";
//        completeTask(approve,taskId);
//
//
//        //领导李四查询自己的流程
//        queryLeaveProcessING(approve);
//
//
//        //李四提交自己的流程
//        completeTask(approve, "e60702be-0d08-11ea-8a0a-9c5c8e7034f6", 1);
//
//
//        //张三查询自己的历史流程
//        queryHistoryTask();
//    }
//
//
//    /**
//     * 开启一个请假流程
//     *
//     * @param user                 用户key
//     * @param processDefinitionKey 流程图key 每一个流程有对应的一个key这个是某一个流程内固定的写在bpmn内的
//     */
//    void startLeaveProcess(String user, String processDefinitionKey) {
//        System.out.println(user + "开启一个请假流程：" + processDefinitionKey);
//        HashMap<String, Object> variables = new HashMap<>();
//        variables.put("user", user);//userKey在上文的流程变量中指定了
//
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
//        System.out.println("流程实例ID:" + instance.getId());
//        System.out.println("流程定义ID:" + instance.getProcessDefinitionId());
//        System.out.println("==================================================================");
//    }
//
//
//    /**
//     * 查询当前任务流程
//     */
//    void queryLeaveProcessING(String assignee) {
//        System.out.println(assignee + "查询自己当前的流程：");
//        List<Task> list = taskService.createTaskQuery()//创建任务查询对象
//                .taskAssignee(assignee)//指定个人任务查询
//                .list();
//        if (list != null && list.size() > 0) {
//            for (Task task : list) {
//                System.out.println("任务ID:" + task.getId());
//                System.out.println("任务名称:" + task.getName());
//                System.out.println("任务的创建时间:" + task.getCreateTime());
//                System.out.println("任务的办理人:" + task.getAssignee());
//                System.out.println("流程实例ID：" + task.getProcessInstanceId());
//                System.out.println("执行对象ID:" + task.getExecutionId());
//                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
//                Map<String, Object> map = task.getProcessVariables();
//                for (Map.Entry<String, Object> m : map.entrySet()) {
//                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
//                }
//                for (Map.Entry<String, Object> m : task.getTaskLocalVariables().entrySet()) {
//                    System.out.println("key:" + m.getKey() + " value:" + m.getValue());
//                }
//
//            }
//        }
//        System.out.println("==================================================================");
//    }
//
//
//    @Test
//    void completeTask(String approve, String taskId) {
//        System.out.println(approve + "：提交自己的流程：" + taskId);
//        //任务ID
//        HashMap<String, Object> variables = new HashMap<>();
//        variables.put("approve", approve);//userKey在上文的流程变量中指定了
//        taskService.complete(taskId, variables);
//
//        System.out.println("完成任务：任务ID：" + taskId);
//        System.out.println("==================================================================");
//    }
//
//
//    @Test
//    void completeTask(String user, String taskId, int audit) {
//        System.out.println(user + "：提交自己的流程：" + taskId + " ;是否通过：" + audit);
//        //任务ID
//        HashMap<String, Object> variables = new HashMap<>();
//        variables.put("audit", audit);//userKey在上文的流程变量中指定了
//        taskService.complete(taskId, variables);
//
//        System.out.println("完成任务：任务ID：" + taskId);
//        System.out.println("==================================================================");
//    }
//
//
//    @Test
//    void queryHistoryTask() {
//        List<HistoricTaskInstance> list = historyService // 历史相关Service
//                .createHistoricTaskInstanceQuery() // 创建历史活动实例查询
//                .processInstanceId("34f2f038-0d07-11ea-b319-9c5c8e7034f6") // 执行流程实例id
//                .orderByTaskCreateTime()
//                .asc()
//                .list();
//        for (HistoricTaskInstance hai : list) {
//            System.out.println("活动ID:" + hai.getId());
//            System.out.println("流程实例ID:" + hai.getProcessInstanceId());
//            System.out.println("活动名称：" + hai.getName());
//            System.out.println("办理人：" + hai.getAssignee());
//            System.out.println("开始时间：" + hai.getStartTime());
//            System.out.println("结束时间：" + hai.getEndTime());
//            System.out.println("==================================================================");
//        }
////        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee("张三").orderByTaskCreateTime().asc().list();
////        for(HistoricTaskInstance hai:list){
////            System.out.println("活动ID:"+hai.getId());
////            System.out.println("流程实例ID:"+hai.getProcessInstanceId());
////            System.out.println("活动名称："+hai.getName());
////            System.out.println("办理人："+hai.getAssignee());
////            System.out.println("开始时间："+hai.getStartTime());
////            System.out.println("结束时间："+hai.getEndTime());
////            System.out.println("==================================================================");
////        }
//
//
//    }
//
//}
