/**
 * FileName: DocumentUtil
 * Author:   Administrator
 * Date:     2018/10/24 13:47
 * Description:
 */
package xml;

import org.dom4j.*;

import java.util.Iterator;
import java.util.List;

public class DocumentUtil
{

    enum Tree{
        Organization,Department,Device,Channel,Alarm,Alarmout
    }
    /**
     * xml 转换为document
     * @param xml
     * @return
     */
    public static Document cover2Document(String xml)
    {
        Document doc = null;

        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return doc;
    }

    static Element recursionFindByCoding(Element element, String coding)
    {
        element.element("");
        Iterator iterator = element.attributeIterator();
        String codingCur = null;

        //获取当前department的coding
        while (iterator.hasNext())
        {
            Attribute attr = (Attribute) iterator.next();
            String name = attr.getName();
            if(! name.equals("coding"))
            {
               continue;
            }
            String value = attr.getValue();
            System.out.println(value);
            codingCur = value;
            break;
        }


        if(codingCur.equals(coding.substring(0, codingCur.length())))
        {
            Element nodeSuper;

            nodeSuper = element;

            if(codingCur.equals(coding))
            {
                return element;
            }
            List department = nodeSuper.elements("Department");

            Iterator departments = department.iterator();

            Element codingNode = null;

            while (departments.hasNext())
            {
                Element next = (Element) departments.next();

                Element recursionByCoding = recursionFindByCoding(next, coding);
                if(recursionByCoding != null)
                {
                    return recursionByCoding;
                }

            }

            return codingNode;

        }
        else
        {
            return null;
        }
    }

    static Element recursionRemoveByCoding(Element element, String coding)
    {
        Element elementBycoding = recursionFindByCoding(element, coding);
        if(elementBycoding != null)
        {
            elementBycoding.getParent().remove(elementBycoding);
        }
        Element root = elementBycoding;

        while (root.getParent() != null)
        {
            root = root.getParent();
        }

        return root;
    }


    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
                "<Organization grade=\"0\" modifiedTime=\"1540280958804\">\n" +
                "    <Department coding=\"1001\" domainId=\"1\" id=\"1\" name=\"福建省电力公司\" type=\"\">\n" +
                "        <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001\" desc=\"\" domainId=\"0\" id=\"1100001000002\" ip=\"192.168.1.117\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"IPC测试\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "            <Channel camera=\"1\" id=\"110000100000201\" num=\"1\" title=\"通道1\" type=\"0\"/>\n" +
                "            <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "            <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "        </Device>\n" +
                "        <Device alert=\"1\" alertout=\"0\" channel=\"1\" coding=\"1001\" desc=\"\" domainId=\"0\" id=\"1100001000655\" ip=\"1.210\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1\" title=\"1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "            <Channel camera=\"1\" id=\"110000100065501\" num=\"1\" title=\"通道1\" type=\"0\"/>\n" +
                "            <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "        </Device>\n" +
                "        <Department coding=\"1001001\" domainId=\"0\" id=\"2\" name=\"福州供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001001001\" domainId=\"0\" id=\"11\" name=\"福州大学旗山校区充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001001\" desc=\"\" domainId=\"0\" id=\"1100001000026\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福大车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100002601\" num=\"1\" title=\"福大车位检测1号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"6\" alertout=\"0\" channel=\"6\" coding=\"1001001001\" desc=\"\" domainId=\"0\" id=\"1100001000027\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6$9004:1,2,3,4,5,6$9002:1,2,3,4,5,6$9007:1,2,3,4,5,6\" title=\"福大NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100002701\" num=\"1\" title=\"福大球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100002702\" num=\"2\" title=\"福大车位检测1号\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100002703\" num=\"3\" title=\"福大车位检测2号\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100002704\" num=\"4\" title=\"福大车位检测3号\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100002705\" num=\"5\" title=\"福大车位检测4号\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100002706\" num=\"6\" title=\"福大车位检测5号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                </Device>") ;
        stringBuilder.append(" <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001001\" desc=\"\" domainId=\"0\" id=\"1100001000032\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福大车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100003201\" num=\"1\" title=\"福大车位检测2号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001001\" desc=\"\" domainId=\"0\" id=\"1100001000037\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福大车位检测3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100003701\" num=\"1\" title=\"福大车位检测3号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001001\" desc=\"\" domainId=\"0\" id=\"1100001000039\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福大车位检测4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100003901\" num=\"1\" title=\"福大车位检测4号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001001\" desc=\"\" domainId=\"0\" id=\"1100001000041\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福大车位检测5号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100004101\" num=\"1\" title=\"福大车位检测5号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001002\" domainId=\"0\" id=\"12\" name=\"鼓山风景区管委会专用充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001002\" desc=\"\" domainId=\"0\" id=\"1100001000049\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"鼓山球机NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100004901\" num=\"1\" title=\"鼓山球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100004902\" num=\"2\" title=\"鼓山车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100004903\" num=\"3\" title=\"鼓山车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100004904\" num=\"4\" title=\"鼓山车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100004905\" num=\"5\" title=\"鼓山车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001002\" desc=\"\" domainId=\"0\" id=\"1100001000054\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"鼓山车位检测器1号\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"鼓山车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100005401\" num=\"1\" title=\"鼓山车位检测器1号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001002\" desc=\"\" domainId=\"0\" id=\"1100001000056\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"鼓山车位检测器2号\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"鼓山车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100005601\" num=\"1\" title=\"鼓山车位检测器2号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001002\" desc=\"\" domainId=\"0\" id=\"1100001000058\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"鼓山车位检测器3号\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"鼓山车位检测3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100005801\" num=\"1\" title=\"鼓山车位检测器3号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001002\" desc=\"\" domainId=\"0\" id=\"1100001000060\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"鼓山车位检测器4号\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"鼓山车位检测4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100006001\" num=\"1\" title=\"鼓山车位检测器4号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>");
        stringBuilder.append("\n" +
                "            <Department coding=\"1001001003\" domainId=\"0\" id=\"128\" name=\"连江供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001001003001\" domainId=\"0\" id=\"129\" name=\"连江县委县政府专用充电站\" type=\"\">\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001003001\" desc=\"\" domainId=\"0\" id=\"1100001000097\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江行政服务中心车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100009701\" num=\"1\" title=\"连江行政服务中心车位检测1号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001003001\" desc=\"\" domainId=\"0\" id=\"1100001000100\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"连江行政服务中心球机NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100010001\" num=\"1\" title=\"连江行政服务中心球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010002\" num=\"2\" title=\"连江行政服务中心车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010003\" num=\"3\" title=\"连江行政服务中心车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010004\" num=\"4\" title=\"连江行政服务中心车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010005\" num=\"5\" title=\"连江行政服务中心车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001003001\" desc=\"\" domainId=\"0\" id=\"1100001000107\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江行政服务中心车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100010701\" num=\"1\" title=\"连江行政服务中心车位检测2号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001003001\" desc=\"\" domainId=\"0\" id=\"1100001000109\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江行政服务中心车位检测3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100010901\" num=\"1\" title=\"连江行政服务中心车位检测3号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001003001\" desc=\"\" domainId=\"0\" id=\"1100001000111\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江行政服务中心车位检测4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100011101\" num=\"1\" title=\"连江行政服务中心车位检测4号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001004\" domainId=\"0\" id=\"130\" name=\"永泰供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001001004001\" domainId=\"0\" id=\"131\" name=\"永泰供电公司百漈沟城市充电站\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001004001\" desc=\"\" domainId=\"0\" id=\"1100001000417\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"永泰供电公司百漈沟城市充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100041701\" num=\"1\" title=\"永泰供电公司百漈沟城市充电站球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041702\" num=\"2\" title=\"永泰供电公司百漈沟风景区车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041703\" num=\"3\" title=\"永泰供电公司百漈沟风景区车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041704\" num=\"4\" title=\"永泰供电公司百漈沟风景区车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041705\" num=\"5\" title=\"永泰供电公司百漈沟风景区车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001004001\" desc=\"\" domainId=\"0\" id=\"1100001000419\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永泰供电公司百漈沟风景区车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100041901\" num=\"1\" title=\"永泰供电公司百漈沟风景区车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001004001\" desc=\"\" domainId=\"0\" id=\"1100001000421\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永泰供电公司百漈沟风景区车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042101\" num=\"1\" title=\"永泰供电公司百漈沟风景区车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001004001\" desc=\"\" domainId=\"0\" id=\"1100001000424\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永泰供电公司百漈沟风景区车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042401\" num=\"1\" title=\"永泰供电公司百漈沟风景区车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001004001\" desc=\"\" domainId=\"0\" id=\"1100001000426\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永泰供电公司百漈沟风景区车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042601\" num=\"1\" title=\"永泰供电公司百漈沟风景区车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001005\" domainId=\"0\" id=\"18\" name=\"福州马尾青州公交充电站\" type=\"\">\n" +
                "                <Device alert=\"10\" alertout=\"0\" channel=\"10\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000915\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10$9004:1,2,3,4,5,6,7,8,9,10$9002:1,2,3,4,5,6,7,8,9,10$9007:1,2,3,4,5,6,7,8,9,10\" title=\"马尾青州公交充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100091501\" num=\"1\" title=\"马尾青州公交充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091502\" num=\"2\" title=\"马尾青州公交充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091503\" num=\"3\" title=\"马尾青州公交充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091504\" num=\"4\" title=\"马尾青州公交充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091505\" num=\"5\" title=\"马尾青州公交充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091506\" num=\"6\" title=\"车马尾青州公交充电站位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091507\" num=\"7\" title=\"马尾青州公交充电站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091508\" num=\"8\" title=\"马尾青州公交充电站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091509\" num=\"9\" title=\"马尾青州公交充电站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100091510\" num=\"10\" title=\"马尾青州公交充电站360球机2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000916\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100091601\" num=\"1\" title=\"马尾青州公交站车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000918\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100091801\" num=\"1\" title=\"马尾青州公交站车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000920\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交车站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100092001\" num=\"1\" title=\"马尾青州公交车站车位检测3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000922\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交车站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100092201\" num=\"1\" title=\"马尾青州公交车站车位检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000925\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100092501\" num=\"1\" title=\"马尾青州公交站车位检测5\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000926\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100092601\" num=\"1\" title=\"马尾青州公交站车位检测6\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000927\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100092701\" num=\"1\" title=\"马尾青州公交站车位检测7\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001005\" desc=\"\" domainId=\"0\" id=\"1100001000933\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"马尾青州公交站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100093301\" num=\"1\" title=\"马尾青州公交站车位检测8\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>");
        stringBuilder.append("<Department coding=\"1001001006\" domainId=\"0\" id=\"19\" name=\"福州供电公司长乐营前服务区城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001006\" desc=\"\" domainId=\"0\" id=\"1100001000142\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"长乐营前服务区NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100014201\" num=\"1\" title=\"长乐营前服务区球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014202\" num=\"2\" title=\"长乐营前服务区车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014203\" num=\"3\" title=\"长乐营前服务区车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014204\" num=\"4\" title=\"长乐营前服务区车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014205\" num=\"5\" title=\"长乐营前服务区车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001006\" desc=\"\" domainId=\"0\" id=\"1100001000166\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"长乐营前服务区车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100016601\" num=\"1\" title=\"长乐营前服务区车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001006\" desc=\"\" domainId=\"0\" id=\"1100001000171\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"长乐营前服务区车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017101\" num=\"1\" title=\"长乐营前服务区车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001006\" desc=\"\" domainId=\"0\" id=\"1100001000174\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"长乐营前服务区车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017401\" num=\"1\" title=\"长乐营前服务区车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001006\" desc=\"\" domainId=\"0\" id=\"1100001000177\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"长乐营前服务区车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017701\" num=\"1\" title=\"长乐营前服务区车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001007\" domainId=\"0\" id=\"20\" name=\"福州供电公司7#城市洋恰充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001007\" desc=\"\" domainId=\"0\" id=\"1100001000447\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"福州7#城市充电站洋洽NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100044701\" num=\"1\" title=\"福州供电公司7#城市充电站洋洽NVR球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044702\" num=\"2\" title=\"福州供电公司7#城市充电站洋洽车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044703\" num=\"3\" title=\"福州供电公司7#城市充电站洋洽车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044704\" num=\"4\" title=\"福州供电公司7#城市充电站洋洽车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044705\" num=\"5\" title=\"福州供电公司7#城市充电站洋洽车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001007\" desc=\"\" domainId=\"0\" id=\"1100001000452\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州7#城市充电站洋洽车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045201\" num=\"1\" title=\"福州7#城市充电站洋洽车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001007\" desc=\"\" domainId=\"0\" id=\"1100001000454\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州7#城市充电站洋洽车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045401\" num=\"1\" title=\"福州7#城市充电站洋洽车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001007\" desc=\"\" domainId=\"0\" id=\"1100001000456\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州7#城市充电站洋洽车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045601\" num=\"1\" title=\"福州7#城市充电站洋洽车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001007\" desc=\"\" domainId=\"0\" id=\"1100001000460\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州7#城市充电站洋洽车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100046001\" num=\"1\" title=\"福州7#城市充电站洋洽车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001008\" domainId=\"0\" id=\"21\" name=\"福州供电公司连江体育公园城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001008\" desc=\"\" domainId=\"0\" id=\"1100001000210\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"连江县体育公园NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021001\" num=\"1\" title=\"连江县体育公园球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021002\" num=\"2\" title=\"连江县体育公园车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021003\" num=\"3\" title=\"连江县体育公园车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021004\" num=\"4\" title=\"连江县体育公园车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021005\" num=\"5\" title=\"连江县体育公园车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001008\" desc=\"\" domainId=\"0\" id=\"1100001000213\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江县体育公园车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021301\" num=\"1\" title=\"连江县体育公园车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001008\" desc=\"\" domainId=\"0\" id=\"1100001000218\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江县体育公园车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021801\" num=\"1\" title=\"连江县体育公园车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001008\" desc=\"\" domainId=\"0\" id=\"1100001000220\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江县体育公园车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100022001\" num=\"1\" title=\"连江县体育公园车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001008\" desc=\"\" domainId=\"0\" id=\"1100001000259\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"连江县体育公园车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100025901\" num=\"1\" title=\"连江县体育公园车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001009\" domainId=\"0\" id=\"22\" name=\"福州东湖大院城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001009\" desc=\"\" domainId=\"0\" id=\"1100001000400\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"福州东湖大院城市充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040001\" num=\"1\" title=\"福州东湖大院城市充电站球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100040002\" num=\"2\" title=\"福州东湖大院城市充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100040003\" num=\"3\" title=\"福州东湖大院城市充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100040004\" num=\"4\" title=\"福州东湖大院城市充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100040005\" num=\"5\" title=\"福州东湖大院城市充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001009\" desc=\"\" domainId=\"0\" id=\"1100001000403\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州省直东湖大院车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040301\" num=\"1\" title=\"福州省直东湖大院车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001009\" desc=\"\" domainId=\"0\" id=\"1100001000405\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州省直东湖大院车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040501\" num=\"1\" title=\"福州省直东湖大院车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001009\" desc=\"\" domainId=\"0\" id=\"1100001000407\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州省直东湖大院车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040701\" num=\"1\" title=\"福州省直东湖大院车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001009\" desc=\"\" domainId=\"0\" id=\"1100001000409\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州省直东湖大院车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040901\" num=\"1\" title=\"福州省直东湖大院车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001010\" domainId=\"0\" id=\"201\" name=\"公司测试\" type=\"\"/>\n" +
                "            <Department coding=\"1001001011\" domainId=\"0\" id=\"24\" name=\"福州供电公司10#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001001011\" desc=\"\" domainId=\"0\" id=\"1100001001029\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"福州仓山区铁塔公司充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100102901\" num=\"1\" title=\"福州仓山区铁塔公司充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100102902\" num=\"2\" title=\"福州仓山区铁塔公司充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100102903\" num=\"3\" title=\"福州仓山区铁塔公司充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100102904\" num=\"4\" title=\"福州仓山区铁塔公司充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100102905\" num=\"5\" title=\"福州仓山区铁塔公司充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001011\" desc=\"\" domainId=\"0\" id=\"1100001001032\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州仓山区铁塔公司充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100103201\" num=\"1\" title=\"福州仓山区铁塔公司充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001011\" desc=\"\" domainId=\"0\" id=\"1100001001034\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州仓山区铁塔公司充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100103401\" num=\"1\" title=\"福州仓山区铁塔公司充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001012\" domainId=\"0\" id=\"25\" name=\"福州供电公司11#城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001001013\" domainId=\"0\" id=\"121\" name=\"闽清供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001001013001\" domainId=\"0\" id=\"127\" name=\"福州供电公司4#公交车充电站\" type=\"\">\n" +
                "                    <Device alert=\"6\" alertout=\"0\" channel=\"6\" coding=\"1001001013001\" desc=\"\" domainId=\"0\" id=\"1100001000068\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6$9004:1,2,3,4,5,6$9002:1,2,3,4,5,6$9007:1,2,3,4,5,6\" title=\"闽清北4#公交站充电站球机NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100006801\" num=\"1\" title=\"闽清北4#公交站充电站球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100006802\" num=\"2\" title=\"闽清北4#公交站充电站车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100006803\" num=\"3\" title=\"闽清北4#公交站充电站车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100006804\" num=\"4\" title=\"闽清北4#公交站充电站车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100006805\" num=\"5\" title=\"闽清北4#公交站充电站车位检测4\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100006806\" num=\"6\" title=\"闽清北4#公交站充电站车位检测5\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001013001\" desc=\"\" domainId=\"0\" id=\"1100001000076\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽清公交站车位检测器1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100007601\" num=\"1\" title=\"闽清公交站车位检测器1号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001013001\" desc=\"\" domainId=\"0\" id=\"1100001000081\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽清公交站车位检测器2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100008101\" num=\"1\" title=\"闽清公交站车位检测器2号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001013001\" desc=\"\" domainId=\"0\" id=\"1100001000083\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽清公交站车位检测器3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100008301\" num=\"1\" title=\"闽清公交站车位检测器3号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001013001\" desc=\"\" domainId=\"0\" id=\"1100001000085\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽清公交站车位检测器5号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100008501\" num=\"1\" title=\"闽清公交站车位检测器5号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001013001\" desc=\"\" domainId=\"0\" id=\"1100001000089\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽清公交站车位检测器4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100008901\" num=\"1\" title=\"闽清公交站车位检测器4号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001014\" domainId=\"0\" id=\"240\" name=\"公司测试用\" type=\"\"/>\n" +
                "            <Department coding=\"1001001015\" domainId=\"0\" id=\"301\" name=\"福州化工公交充电站\" type=\"\">\n" +
                "                <Device alert=\"10\" alertout=\"0\" channel=\"10\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000983\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10$9004:1,2,3,4,5,6,7,8,9,10$9002:1,2,3,4,5,6,7,8,9,10$9007:1,2,3,4,5,6,7,8,9,10\" title=\"福州化工公交充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100098301\" num=\"1\" title=\"福州化工公交充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098302\" num=\"2\" title=\"福州化工公交充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098303\" num=\"3\" title=\"福州化工公交充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098304\" num=\"4\" title=\"福州化工公交充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098305\" num=\"5\" title=\"福州化工公交充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098306\" num=\"6\" title=\"福州化工公交充电站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098307\" num=\"7\" title=\"福州化工公交充电站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098308\" num=\"8\" title=\"福州化工公交充电站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098309\" num=\"9\" title=\"福州化工公交充电站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100098310\" num=\"10\" title=\"福州化工公交充电站360球机1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000985\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100098501\" num=\"1\" title=\"福州化工路公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000987\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100098701\" num=\"1\" title=\"福州化工路公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000989\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100098901\" num=\"1\" title=\"福州化工路公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000991\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100099101\" num=\"1\" title=\"福州化工路公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000993\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100099301\" num=\"1\" title=\"福州化工路公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000995\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100099501\" num=\"1\" title=\"福州化工路公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000997\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100099701\" num=\"1\" title=\"福州化工路公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001015\" desc=\"\" domainId=\"0\" id=\"1100001000999\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州化工路公交站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100099901\" num=\"1\" title=\"福州化工路公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>");
        stringBuilder.append("\n" +
                "            <Department coding=\"1001001016\" domainId=\"0\" id=\"312\" name=\"福州义序公交充电\" type=\"\"/>\n" +
                "            <Department coding=\"1001001017\" domainId=\"0\" id=\"311\" name=\"福州快安公交充电站\" type=\"\">\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"10\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001008\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10$9004:1,2,3,4,5,6,7,8,9,10$9002:1,2,3,4,5,6,7,8,9,10\" title=\"福州快安公交充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100100801\" num=\"1\" title=\"福州快安公交充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100802\" num=\"2\" title=\"福州快安公交充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100803\" num=\"3\" title=\"福州快安公交充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100804\" num=\"4\" title=\"福州快安公交充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100805\" num=\"5\" title=\"福州快安公交充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100806\" num=\"6\" title=\"福州快安公交充电站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100807\" num=\"7\" title=\"福州快安公交充电站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100808\" num=\"8\" title=\"福州快安公交充电站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100809\" num=\"9\" title=\"福州快安公交充电站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100100810\" num=\"10\" title=\"福州快安公交充电站360球机1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001010\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100101001\" num=\"1\" title=\"福州快安公交充电站车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001011\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100101101\" num=\"1\" title=\"福州快安公交充电站车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001012\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100101201\" num=\"1\" title=\"福州快安公交充电站车位检测3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001014\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100101401\" num=\"1\" title=\"福州快安公交充电站车位检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001015\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100101501\" num=\"1\" title=\"福州快安公交充电站车位检测5\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001020\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100102001\" num=\"1\" title=\"福州快安公交充电站车位检测6\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001021\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100102101\" num=\"1\" title=\"福州快安公交充电站车位检测7\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001017\" desc=\"\" domainId=\"0\" id=\"1100001001023\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州快安公交充电站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100102301\" num=\"1\" title=\"福州快安公交充电站车位检测8\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001018\" domainId=\"0\" id=\"313\" name=\"福州琴亭（一期）公交充\" type=\"\">\n" +
                "                <Device alert=\"10\" alertout=\"0\" channel=\"10\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000937\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10$9004:1,2,3,4,5,6,7,8,9,10$9002:1,2,3,4,5,6,7,8,9,10$9007:1,2,3,4,5,6,7,8,9,10\" title=\"福州琴亭一期公交站NVR1\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100093701\" num=\"1\" title=\"福州琴亭一期公交站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093702\" num=\"2\" title=\"福州琴亭一期公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093703\" num=\"3\" title=\"福州琴亭一期公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093704\" num=\"4\" title=\"福州琴亭一期公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093705\" num=\"5\" title=\"福州琴亭一期公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093706\" num=\"6\" title=\"福州琴亭一期公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093707\" num=\"7\" title=\"福州琴亭一期公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093708\" num=\"8\" title=\"福州琴亭一期公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093709\" num=\"9\" title=\"福州琴亭一期公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093710\" num=\"10\" title=\"福州琴亭一期公交站360普通球机1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000942\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭一期公交站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100094201\" num=\"1\" title=\"福州琴亭一期公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000945\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭一期公交站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100094501\" num=\"1\" title=\"福州琴亭一期公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000947\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭一期公交站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100094701\" num=\"1\" title=\"福州琴亭一期公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000949\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭一期公交站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100094901\" num=\"1\" title=\"福州琴亭一期公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000952\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭一期公交站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100095201\" num=\"1\" title=\"福州琴亭一期公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001000954\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭一期公交站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100095401\" num=\"1\" title=\"福州琴亭一期公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001001001\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州琴亭一期公交站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100100101\" num=\"1\" title=\"福州琴亭一期公交站车位检测7\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001001018\" desc=\"\" domainId=\"0\" id=\"1100001001002\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"福州琴亭一期公交站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100100201\" num=\"1\" title=\"福州琴亭一期公交站车位检测8\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001001019\" domainId=\"0\" id=\"321\" name=\"福州琴亭（二期）公交站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000619\" ip=\"192.168.1.29\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061901\" num=\"1\" title=\"福州琴亭二期公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"10\" alertout=\"0\" channel=\"10\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000939\" ip=\"192.168.1.8\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10$9004:1,2,3,4,5,6,7,8,9,10$9002:1,2,3,4,5,6,7,8,9,10$9007:1,2,3,4,5,6,7,8,9,10\" title=\"福州琴亭二期公交站NVR2\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100093901\" num=\"1\" title=\"福州琴亭二期公交站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093902\" num=\"2\" title=\"福州琴亭二期公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093903\" num=\"3\" title=\"福州琴亭二期公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093904\" num=\"4\" title=\"福州琴亭二期公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093905\" num=\"5\" title=\"福州琴亭二期公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093906\" num=\"6\" title=\"福州琴亭二期公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093907\" num=\"7\" title=\"福州琴亭二期公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093908\" num=\"8\" title=\"福州琴亭二期公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093909\" num=\"9\" title=\"福州琴亭二期公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100093910\" num=\"10\" title=\"福州琴亭二期公交站普通360球机2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000960\" ip=\"192.168.1.23\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100096001\" num=\"1\" title=\"福州琴亭二期公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000962\" ip=\"192.168.1.24\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100096201\" num=\"1\" title=\"福州琴亭二期公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000964\" ip=\"192.168.1.25\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100096401\" num=\"1\" title=\"福州琴亭二期公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000967\" ip=\"192.168.1.26\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100096701\" num=\"1\" title=\"福州琴亭二期公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000970\" ip=\"192.168.1.27\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100097001\" num=\"1\" title=\"福州琴亭二期公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000976\" ip=\"192.168.1.28\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100097601\" num=\"1\" title=\"福州琴亭二期公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001001019\" desc=\"\" domainId=\"0\" id=\"1100001000979\" ip=\"192.168.1.30\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福州琴亭二期公交站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100097901\" num=\"1\" title=\"福州琴亭二期公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "        </Department>\n" );stringBuilder.append(" <Department coding=\"1001002\" domainId=\"0\" id=\"3\" name=\"厦门供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001002001\" domainId=\"0\" id=\"101\" name=\"厦门市集美区后溪花园充电站\" type=\"\">\n" +
                "                <Device alert=\"8\" alertout=\"0\" channel=\"8\" coding=\"1001002001\" desc=\"\" domainId=\"0\" id=\"1100001000455\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8$9004:1,2,3,4,5,6,7,8$9002:1,2,3,4,5,6,7,8$9007:1,2,3,4,5,6,7,8\" title=\"厦门集美后溪花园NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045501\" num=\"1\" title=\"厦门集美后溪花园NVR\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045502\" num=\"2\" title=\"厦门集美后溪花园气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045503\" num=\"3\" title=\"厦门集美后溪花园普通球机1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045504\" num=\"4\" title=\"厦门集美后溪花园普通球机2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045505\" num=\"5\" title=\"厦门集美后溪花园普通球机3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045506\" num=\"6\" title=\"厦门集美后溪花园车位检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045507\" num=\"7\" title=\"厦门集美后溪花园车位检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100045508\" num=\"8\" title=\"厦门集美后溪花园车位检测器3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002001\" desc=\"\" domainId=\"0\" id=\"1100001000457\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"集美后溪花园车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045701\" num=\"1\" title=\"集美后溪花园车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002001\" desc=\"\" domainId=\"0\" id=\"1100001000459\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"集美后溪花园车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045901\" num=\"1\" title=\"集美后溪花园车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002001\" desc=\"\" domainId=\"0\" id=\"1100001000461\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"集美后溪花园车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100046101\" num=\"1\" title=\"集美后溪花园车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001002002\" domainId=\"0\" id=\"102\" name=\"厦门市海沧区滨湖充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000069\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100006901\" num=\"1\" title=\"海沧滨湖充电站车位检测1号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000072\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100007201\" num=\"1\" title=\"海沧滨湖充电站车位检测2号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000078\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100007801\" num=\"1\" title=\"海沧滨湖充电站车位检测3号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000080\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100008001\" num=\"1\" title=\"海沧滨湖充电站车位检测4号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000087\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测5号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100008701\" num=\"1\" title=\"海沧滨湖充电站车位检测5号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000090\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测6号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100009001\" num=\"1\" title=\"海沧滨湖充电站车位检测6号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000093\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测7号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100009301\" num=\"1\" title=\"海沧滨湖充电站车位检测7号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000099\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测8号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100009901\" num=\"1\" title=\"海沧滨湖充电站车位检测8号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"24\" alertout=\"0\" channel=\"24\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000104\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24$9004:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24$9002:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24$9007:1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24\" title=\"海沧滨湖充电站球机NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100010401\" num=\"1\" title=\"海沧滨湖充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010402\" num=\"2\" title=\"海沧滨湖充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010403\" num=\"3\" title=\"海沧滨湖充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010404\" num=\"4\" title=\"海沧滨湖充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010405\" num=\"5\" title=\"海沧滨湖充电站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010406\" num=\"6\" title=\"海沧滨湖充电站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010407\" num=\"7\" title=\"海沧滨湖充电站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010408\" num=\"8\" title=\"海沧滨湖充电站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010409\" num=\"9\" title=\"海沧滨湖充电站车位检测9\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010410\" num=\"10\" title=\"海沧滨湖充电站车位检测10\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010411\" num=\"11\" title=\"海沧滨湖充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010412\" num=\"12\" title=\"海沧滨湖充电站大门监控\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010413\" num=\"13\" title=\"海沧滨湖充电站360球机2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010414\" num=\"14\" title=\"海沧滨湖充电站360球机3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010415\" num=\"15\" title=\"海沧滨湖充电站360球机4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010416\" num=\"16\" title=\"海沧滨湖充电站360球机5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010417\" num=\"17\" title=\"海沧滨湖充电站360球机6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010418\" num=\"18\" title=\"海沧滨湖充电站360球机7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010419\" num=\"19\" title=\"海沧滨湖充电站360球机8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010420\" num=\"20\" title=\"海沧滨湖充电站360球机9\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010421\" num=\"21\" title=\"海沧滨湖充电站360球机10\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010422\" num=\"22\" title=\"通道22\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010423\" num=\"23\" title=\"通道23\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100010424\" num=\"24\" title=\"通道24\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"11\" title=\"报警输入11\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"12\" title=\"报警输入12\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"13\" title=\"报警输入13\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"14\" title=\"报警输入14\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"15\" title=\"报警输入15\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"16\" title=\"报警输入16\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"17\" title=\"报警输入17\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"18\" title=\"报警输入18\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"19\" title=\"报警输入19\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"20\" title=\"报警输入20\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"21\" title=\"报警输入21\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"22\" title=\"报警输入22\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"23\" title=\"报警输入23\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"24\" title=\"报警输入24\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000318\" ip=\"192.168.1.19\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测9号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100031801\" num=\"1\" title=\"海沧滨湖充电站车位检测9号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002002\" desc=\"\" domainId=\"0\" id=\"1100001000320\" ip=\"192.168.1.20\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"海沧滨湖充电站车位检测10号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100032001\" num=\"1\" title=\"海沧滨湖充电站车位检测10号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" );stringBuilder.append("<Department coding=\"1001002003\" domainId=\"0\" id=\"103\" name=\"厦门市海沧区青礁慈济祖宫充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000222\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"厦门海沧青礁慈济宫NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100022201\" num=\"1\" title=\"厦门海沧青礁慈济宫球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100022202\" num=\"2\" title=\"厦门海沧青礁慈济宫检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100022203\" num=\"3\" title=\"厦门海沧青礁慈济宫检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100022204\" num=\"4\" title=\"厦门海沧青礁慈济宫检测器3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100022205\" num=\"5\" title=\"厦门海沧青礁慈济宫普通球机\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000335\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"厦门海沧青礁慈济宫检测器3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100033501\" num=\"1\" title=\"厦门海沧青礁慈济宫检测器3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000347\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"厦门海沧青礁慈济宫检测器12\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100034701\" num=\"1\" title=\"厦门海沧青礁慈济宫检测器12\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000349\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"厦门海沧青礁慈济宫检测器11\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100034901\" num=\"1\" title=\"厦门海沧青礁慈济宫检测器11\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"4\" alertout=\"0\" channel=\"4\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000351\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"厦门海沧青礁慈济宫NVR2\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4$9004:1,2,3,4$9002:1,2,3,4$9007:1,2,3,4\" title=\"厦门海沧青礁慈济宫NVR2\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035101\" num=\"1\" title=\"海沧青礁慈济宫球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035102\" num=\"2\" title=\"海沧青礁慈济宫检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035103\" num=\"3\" title=\"海沧青礁慈济宫检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035104\" num=\"4\" title=\"厦门海沧青礁慈济宫球机2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000353\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"厦门海沧青礁慈济宫检测11\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035301\" num=\"1\" title=\"厦门青礁慈济宫检测11\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001002003\" desc=\"\" domainId=\"0\" id=\"1100001000355\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"厦门青礁慈济宫检测12\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035501\" num=\"1\" title=\"厦门青礁慈济宫检测12\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001002004\" domainId=\"0\" id=\"104\" name=\"厦门市湖里区围里公寓充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001002005\" domainId=\"0\" id=\"105\" name=\"厦门市湖里区夏商国际充电站\" type=\"\">\n" +
                "                <Device alert=\"6\" alertout=\"0\" channel=\"10\" coding=\"1001002005\" desc=\"\" domainId=\"0\" id=\"1100001000613\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10$9004:1,2,3,4,5,6,7,8,9,10$9002:1,2,3,4,5,6,7,8,9,10$9007:1,2,3,4,5,6\" title=\"厦门灌口供电营业厅NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061301\" num=\"1\" title=\"厦门灌口供电营业厅NVR\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061302\" num=\"2\" title=\"厦门灌口供电营业厅气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061303\" num=\"3\" title=\"厦门灌口供电营业厅普通球机1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061304\" num=\"4\" title=\"厦门灌口供电营业厅普通球机2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061305\" num=\"5\" title=\"厦门灌口供电营业厅普通球机3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061306\" num=\"6\" title=\"厦门灌口供电营业厅车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061307\" num=\"7\" title=\"厦门灌口供电营业厅车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061308\" num=\"8\" title=\"厦门灌口供电营业厅车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061309\" num=\"9\" title=\"厦门灌口供电营业厅车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061310\" num=\"10\" title=\"厦门灌口供电营业厅车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"0\" channel=\"1\" coding=\"1001002005\" desc=\"\" domainId=\"0\" id=\"1100001000614\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1\" title=\"厦门灌口供电营业厅车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061401\" num=\"1\" title=\"厦门灌口供电营业厅车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"0\" channel=\"1\" coding=\"1001002005\" desc=\"\" domainId=\"0\" id=\"1100001000650\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1\" title=\"厦门灌口供电营业厅车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100065001\" num=\"1\" title=\"厦门灌口供电营业厅车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"0\" channel=\"1\" coding=\"1001002005\" desc=\"\" domainId=\"0\" id=\"1100001000651\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1\" title=\"厦门灌口供电营业厅车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100065101\" num=\"1\" title=\"厦门灌口供电营业厅车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"0\" channel=\"1\" coding=\"1001002005\" desc=\"\" domainId=\"0\" id=\"1100001000652\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1\" title=\"厦门灌口供电营业厅车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100065201\" num=\"1\" title=\"厦门灌口供电营业厅车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"0\" channel=\"1\" coding=\"1001002005\" desc=\"\" domainId=\"0\" id=\"1100001000653\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1\" title=\"厦门灌口供电营业厅车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100065301\" num=\"1\" title=\"厦门灌口供电营业厅车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001002006\" domainId=\"0\" id=\"106\" name=\"厦门市同安区军营村充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002006\" desc=\"\" domainId=\"0\" id=\"1100001000429\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"同安区莲花军营充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100042901\" num=\"1\" title=\"同安区莲花军营充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002006\" desc=\"\" domainId=\"0\" id=\"1100001000431\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"同安区莲花军营充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100043101\" num=\"1\" title=\"同安区莲花军营充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001002006\" desc=\"\" domainId=\"0\" id=\"1100001000433\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"同安区莲花军营充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100043301\" num=\"1\" title=\"同安区莲花军营充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"7\" alertout=\"0\" channel=\"7\" coding=\"1001002006\" desc=\"\" domainId=\"0\" id=\"1100001000435\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7$9004:1,2,3,4,5,6,7$9002:1,2,3,4,5,6,7$9007:1,2,3,4,5,6,7\" title=\"厦门市同安区军营村充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100043501\" num=\"1\" title=\"同安区军营村充电站气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100043502\" num=\"2\" title=\"同安区军营村充电站360球机2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100043503\" num=\"3\" title=\"同安区军营村充电站360球机3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100043504\" num=\"4\" title=\"同安区军营村充电站360球机4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100043505\" num=\"5\" title=\"同安区军营村充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100043506\" num=\"6\" title=\"同安区军营村充电站位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100043507\" num=\"7\" title=\"同安区军营村充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "        </Department>\n");stringBuilder.append("<Department coding=\"1001003\" domainId=\"0\" id=\"4\" name=\"莆田供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001003001\" domainId=\"0\" id=\"61\" name=\"莆田供电公司6#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003001\" desc=\"\" domainId=\"0\" id=\"1100001000818\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"莆田6#(城厢区政府)充电站\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100081801\" num=\"1\" title=\"莆田6#(城厢区政府)充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100081802\" num=\"2\" title=\"莆田6#(城厢区政府)充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100081803\" num=\"3\" title=\"莆田6#(城厢区政府)充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100081804\" num=\"4\" title=\"莆田6#(城厢区政府)充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100081805\" num=\"5\" title=\"莆田6#(城厢区政府)充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003001\" desc=\"\" domainId=\"0\" id=\"1100001000819\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"莆田6#(城厢区政府)充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100081901\" num=\"1\" title=\"莆田6#(城厢区政府)充电站车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003001\" desc=\"\" domainId=\"0\" id=\"1100001000820\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"莆田6#(城厢区政府)充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100082001\" num=\"1\" title=\"莆田6#(城厢区政府)充电站车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003001\" desc=\"\" domainId=\"0\" id=\"1100001000821\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"莆田6#(城厢区政府)充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100082101\" num=\"1\" title=\"莆田6#(城厢区政府)充电站车位检测3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003001\" desc=\"\" domainId=\"0\" id=\"1100001000822\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"莆田6#(城厢区政府)充电站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100082201\" num=\"1\" title=\"莆田6#(城厢区政府)充电站检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003002\" domainId=\"0\" id=\"62\" name=\"莆田供电公司1#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003002\" desc=\"\" domainId=\"0\" id=\"1100001000775\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"仙游榜头供电营业厅NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100077501\" num=\"1\" title=\"仙游榜头供电营业厅360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100077502\" num=\"2\" title=\"仙游榜头供电营业厅车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100077503\" num=\"3\" title=\"仙游榜头供电营业厅车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100077504\" num=\"4\" title=\"仙游榜头供电营业厅车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100077505\" num=\"5\" title=\"仙游榜头供电营业厅车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003002\" desc=\"\" domainId=\"0\" id=\"1100001000777\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"仙游榜头供电营业厅车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100077701\" num=\"1\" title=\"仙游榜头供电营业厅车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003002\" desc=\"\" domainId=\"0\" id=\"1100001000779\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"仙游榜头供电营业厅车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100077901\" num=\"1\" title=\"仙游榜头供电营业厅车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003002\" desc=\"\" domainId=\"0\" id=\"1100001000781\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"仙游榜头供电营业厅车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100078101\" num=\"1\" title=\"仙游榜头供电营业厅车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003002\" desc=\"\" domainId=\"0\" id=\"1100001000783\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"仙游榜头供电营业厅车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100078301\" num=\"1\" title=\"仙游榜头供电营业厅车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003003\" domainId=\"0\" id=\"63\" name=\"莆田供电公司2#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003003\" desc=\"\" domainId=\"0\" id=\"1100001000845\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"莆田2#城市(梧塘供电所)NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100084501\" num=\"1\" title=\"莆田2#城市(梧塘供电所)360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100084502\" num=\"2\" title=\"莆田2#城市(梧塘供电所)车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100084503\" num=\"3\" title=\"莆田2#城市(梧塘供电所)车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100084504\" num=\"4\" title=\"莆田2#城市(梧塘供电所)车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100084505\" num=\"5\" title=\"莆田2#城市(梧塘供电所)车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003003\" desc=\"\" domainId=\"0\" id=\"1100001000847\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田2#城市(梧塘供电所)车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100084701\" num=\"1\" title=\"莆田2#城市(梧塘供电所)车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003003\" desc=\"\" domainId=\"0\" id=\"1100001000849\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田2#城市(梧塘供电所)车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100084901\" num=\"1\" title=\"莆田2#城市(梧塘供电所)车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003003\" desc=\"\" domainId=\"0\" id=\"1100001000851\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田2#城市(梧塘供电所)车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100085101\" num=\"1\" title=\"莆田2#城市(梧塘供电所)车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003003\" desc=\"\" domainId=\"0\" id=\"1100001000853\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田2#城市(梧塘供电所)车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100085301\" num=\"1\" title=\"莆田2#城市(梧塘供电所)车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003004\" domainId=\"0\" id=\"64\" name=\"莆田供电公司3#(秀屿供电所)城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003004\" desc=\"\" domainId=\"0\" id=\"1100001000494\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"莆田3#城市(秀屿供电所)NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100049401\" num=\"1\" title=\"莆田3#城市(秀屿供电所)球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100049402\" num=\"2\" title=\"莆田3#城市(秀屿供电所)车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100049403\" num=\"3\" title=\"莆田3#城市(秀屿供电所)车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100049404\" num=\"4\" title=\"莆田3#城市(秀屿供电所)车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100049405\" num=\"5\" title=\"莆田3#城市(秀屿供电所)车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003004\" desc=\"\" domainId=\"0\" id=\"1100001000496\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田3#城市(秀屿供电所)车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100049601\" num=\"1\" title=\"莆田3#城市(秀屿供电所)车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003004\" desc=\"\" domainId=\"0\" id=\"1100001000498\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田3#城市(秀屿供电所)车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100049801\" num=\"1\" title=\"莆田3#城市(秀屿供电所)车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003004\" desc=\"\" domainId=\"0\" id=\"1100001000501\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田3#城市(秀屿供电所)车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100050101\" num=\"1\" title=\"莆田3#城市(秀屿供电所)车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003004\" desc=\"\" domainId=\"0\" id=\"1100001000504\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田3#城市(秀屿供电所)车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100050401\" num=\"1\" title=\"莆田3#城市(秀屿供电所)车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003005\" domainId=\"0\" id=\"65\" name=\"莆田供电公司4#城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001003006\" domainId=\"0\" id=\"66\" name=\"莆田供电公司5#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003006\" desc=\"\" domainId=\"0\" id=\"1100001000825\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"莆田5#(九龙谷森林公园)充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100082501\" num=\"1\" title=\"莆田5#(九龙谷森林公园)充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100082502\" num=\"2\" title=\"莆田5#(九龙谷森林公园)充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100082503\" num=\"3\" title=\"莆田5#(九龙谷森林公园)充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100082504\" num=\"4\" title=\"莆田5#(九龙谷森林公园)充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100082505\" num=\"5\" title=\"莆田5#(九龙谷森林公园)充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003006\" desc=\"\" domainId=\"0\" id=\"1100001000827\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田5#(九龙谷森林公园)车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100082701\" num=\"1\" title=\"莆田5#(九龙谷森林公园)车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003006\" desc=\"\" domainId=\"0\" id=\"1100001000829\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田5#(九龙谷森林公园)车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100082901\" num=\"1\" title=\"莆田5#(九龙谷森林公园)车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003006\" desc=\"\" domainId=\"0\" id=\"1100001000831\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田5#(九龙谷森林公园)车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100083101\" num=\"1\" title=\"莆田5#(九龙谷森林公园)车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003006\" desc=\"\" domainId=\"0\" id=\"1100001000833\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田5#(九龙谷森林公园)车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100083301\" num=\"1\" title=\"莆田5#(九龙谷森林公园)车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003007\" domainId=\"0\" id=\"67\" name=\"莆田供电公司10#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003007\" desc=\"\" domainId=\"0\" id=\"1100001000764\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"白沙供电所充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100076401\" num=\"1\" title=\"白沙供电所充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100076402\" num=\"2\" title=\"白沙供电所充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100076403\" num=\"3\" title=\"白沙供电所充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100076404\" num=\"4\" title=\"白沙供电所充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100076405\" num=\"5\" title=\"白沙供电所充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003007\" desc=\"\" domainId=\"0\" id=\"1100001000766\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"白沙供电所充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100076601\" num=\"1\" title=\"白沙供电所充电站车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003007\" desc=\"\" domainId=\"0\" id=\"1100001000767\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"白沙供电所充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100076701\" num=\"1\" title=\"白沙供电所充电站车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003007\" desc=\"\" domainId=\"0\" id=\"1100001000768\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"白沙供电所充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100076801\" num=\"1\" title=\"白沙供电所充电站车位检测3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001003007\" desc=\"\" domainId=\"0\" id=\"1100001000770\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"白沙供电所充电站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100077001\" num=\"1\" title=\"白沙供电所充电站车位检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003008\" domainId=\"0\" id=\"68\" name=\"莆田供电公司11#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003008\" desc=\"\" domainId=\"0\" id=\"1100001000867\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"莆田黄石设备厂充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100086701\" num=\"1\" title=\"莆田黄石设备厂充电站360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100086702\" num=\"2\" title=\"莆田黄石设备厂充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100086703\" num=\"3\" title=\"莆田黄石设备厂充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100086704\" num=\"4\" title=\"莆田黄石设备厂充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100086705\" num=\"5\" title=\"莆田黄石设备厂充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003008\" desc=\"\" domainId=\"0\" id=\"1100001000870\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田黄石设备厂充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100087001\" num=\"1\" title=\"莆田黄石设备厂充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003008\" desc=\"\" domainId=\"0\" id=\"1100001000872\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田黄石设备厂充电站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100087201\" num=\"1\" title=\"莆田黄石设备厂充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003008\" desc=\"\" domainId=\"0\" id=\"1100001000874\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田黄石设备厂充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100087401\" num=\"1\" title=\"莆田黄石设备厂充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003008\" desc=\"\" domainId=\"0\" id=\"1100001000876\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田黄石设备厂充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100087601\" num=\"1\" title=\"莆田黄石设备厂充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003009\" domainId=\"0\" id=\"69\" name=\"莆炎高速白沙服务区（炎陵方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003009\" desc=\"\" domainId=\"0\" id=\"1100001000293\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"白沙服务区（炎陵方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100029301\" num=\"1\" title=\"白沙服务区（炎陵方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029302\" num=\"2\" title=\"白沙服务区（炎陵方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029303\" num=\"3\" title=\"白沙服务区（炎陵方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029304\" num=\"4\" title=\"白沙服务区（炎陵方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029305\" num=\"5\" title=\"白沙服务区（炎陵方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003009\" desc=\"\" domainId=\"0\" id=\"1100001000296\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（炎陵方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100029601\" num=\"1\" title=\"白沙服务区（炎陵方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003009\" desc=\"\" domainId=\"0\" id=\"1100001000298\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（炎陵方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100029801\" num=\"1\" title=\"白沙服务区（炎陵方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003009\" desc=\"\" domainId=\"0\" id=\"1100001000300\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（炎陵方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100030001\" num=\"1\" title=\"白沙服务区（炎陵方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003009\" desc=\"\" domainId=\"0\" id=\"1100001000302\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（炎陵方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100030201\" num=\"1\" title=\"白沙服务区（炎陵方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003010\" domainId=\"0\" id=\"70\" name=\"莆炎高速白沙服务区（莆田方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003010\" desc=\"\" domainId=\"0\" id=\"1100001000304\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"白沙服务区（莆田方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100030401\" num=\"1\" title=\"白沙服务区（莆田方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100030402\" num=\"2\" title=\"白沙服务区（莆田方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100030403\" num=\"3\" title=\"白沙服务区（莆田方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100030404\" num=\"4\" title=\"白沙服务区（莆田方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100030405\" num=\"5\" title=\"白沙服务区（莆田方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003010\" desc=\"\" domainId=\"0\" id=\"1100001000307\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（莆田方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100030701\" num=\"1\" title=\"白沙服务区（莆田方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003010\" desc=\"\" domainId=\"0\" id=\"1100001000310\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（莆田方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100031001\" num=\"1\" title=\"白沙服务区（莆田方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003010\" desc=\"\" domainId=\"0\" id=\"1100001000312\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（莆田方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100031201\" num=\"1\" title=\"白沙服务区（莆田方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003010\" desc=\"\" domainId=\"0\" id=\"1100001000314\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"白沙服务区（莆田方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100031401\" num=\"1\" title=\"白沙服务区（莆田方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001003011\" domainId=\"0\" id=\"71\" name=\"莆田供电公司专用充电桩\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001003011\" desc=\"\" domainId=\"0\" id=\"1100001000280\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"莆田市政府NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028001\" num=\"1\" title=\"莆田市政府球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100028002\" num=\"2\" title=\"莆田市政府车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100028003\" num=\"3\" title=\"莆田市政府车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100028004\" num=\"4\" title=\"莆田市政府车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100028005\" num=\"5\" title=\"莆田市政府车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003011\" desc=\"\" domainId=\"0\" id=\"1100001000283\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田市政府车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028301\" num=\"1\" title=\"莆田市政府车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003011\" desc=\"\" domainId=\"0\" id=\"1100001000285\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田市政府车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028501\" num=\"1\" title=\"莆田市政府车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003011\" desc=\"\" domainId=\"0\" id=\"1100001000287\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田市政府车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028701\" num=\"1\" title=\"莆田市政府车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001003011\" desc=\"\" domainId=\"0\" id=\"1100001000289\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"莆田市政府车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028901\" num=\"1\" title=\"莆田市政府车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "        </Department>");
        stringBuilder.append("\n" +
                "        <Department coding=\"1001004\" domainId=\"0\" id=\"5\" name=\"泉州供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001004001\" domainId=\"0\" id=\"72\" name=\"南石高速榕桥B服务区（南安方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004001\" desc=\"\" domainId=\"0\" id=\"1100001000234\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"榕桥B服务区（南安方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100023401\" num=\"1\" title=\"榕桥B服务区（南安方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004001\" desc=\"\" domainId=\"0\" id=\"1100001000237\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"榕桥B服务区（南安方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100023701\" num=\"1\" title=\"榕桥B服务区（南安方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004001\" desc=\"\" domainId=\"0\" id=\"1100001000239\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"榕桥B服务区（南安方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100023901\" num=\"1\" title=\"榕桥B服务区（南安方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004001\" desc=\"\" domainId=\"0\" id=\"1100001000242\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"榕桥B服务区（南安方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100024201\" num=\"1\" title=\"榕桥B服务区（南安方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004001\" desc=\"\" domainId=\"0\" id=\"1100001000244\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"榕桥B服务区（南安方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100024401\" num=\"1\" title=\"榕桥B服务区（南安方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100024402\" num=\"2\" title=\"榕桥B服务区（南安方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100024403\" num=\"3\" title=\"榕桥B服务区（南安方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100024404\" num=\"4\" title=\"榕桥B服务区（南安方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100024405\" num=\"5\" title=\"榕桥B服务区（南安方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004002\" domainId=\"0\" id=\"73\" name=\"南石高速榕桥A服务区（石狮方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"3\" alertout=\"0\" channel=\"3\" coding=\"1001004002\" desc=\"\" domainId=\"0\" id=\"1100001000223\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3$9004:1,2,3$9002:1,2,3$9007:1,2,3\" title=\"榕桥A服务区（石狮方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100022301\" num=\"1\" title=\"榕桥A服务区（石狮方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100022302\" num=\"2\" title=\"榕桥A服务区（石狮方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100022303\" num=\"3\" title=\"榕桥A服务区（石狮方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004002\" desc=\"\" domainId=\"0\" id=\"1100001000225\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"榕桥A服务区（石狮方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100022501\" num=\"1\" title=\"榕桥A服务区（石狮方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004002\" desc=\"\" domainId=\"0\" id=\"1100001000227\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"榕桥A服务区（石狮方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100022701\" num=\"1\" title=\"榕桥A服务区（石狮方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004003\" domainId=\"0\" id=\"74\" name=\"南惠高速上田B服务区（南安方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004003\" desc=\"\" domainId=\"0\" id=\"1100001000212\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"上田B服务区（南安方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021201\" num=\"1\" title=\"上田B服务区（南安方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021202\" num=\"2\" title=\"上田B服务区（南安方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021203\" num=\"3\" title=\"上田B服务区（南安方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021204\" num=\"4\" title=\"上田B服务区（南安方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100021205\" num=\"5\" title=\"上田B服务区（南安方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004003\" desc=\"\" domainId=\"0\" id=\"1100001000215\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田B服务区（南安方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021501\" num=\"1\" title=\"上田服务区B车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004003\" desc=\"\" domainId=\"0\" id=\"1100001000217\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田B服务区（南安方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021701\" num=\"1\" title=\"上田服务区B车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004003\" desc=\"\" domainId=\"0\" id=\"1100001000219\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田B服务区（南安方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100021901\" num=\"1\" title=\"上田服务区B车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004003\" desc=\"\" domainId=\"0\" id=\"1100001000221\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田B服务区（南安方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100022101\" num=\"1\" title=\"上田服务区B车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004004\" domainId=\"0\" id=\"75\" name=\"南惠高速上田A服务区（惠安方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004004\" desc=\"\" domainId=\"0\" id=\"1100001000198\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"上田A服务区（惠安方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100019801\" num=\"1\" title=\"南惠高速上田A服务区（惠安方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100019802\" num=\"2\" title=\"上田A服务区（惠安方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100019803\" num=\"3\" title=\"上田A服务区（惠安方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100019804\" num=\"4\" title=\"上田A服务区（惠安方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100019805\" num=\"5\" title=\"上田A服务区（惠安方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004004\" desc=\"\" domainId=\"0\" id=\"1100001000200\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田A服务区（惠安方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020001\" num=\"1\" title=\"上田A服务区（惠安方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004004\" desc=\"\" domainId=\"0\" id=\"1100001000202\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田A服务区（惠安方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020201\" num=\"1\" title=\"上田A服务区（惠安方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004004\" desc=\"\" domainId=\"0\" id=\"1100001000206\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田A服务区（惠安方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020601\" num=\"1\" title=\"上田A服务区（惠安方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004004\" desc=\"\" domainId=\"0\" id=\"1100001000209\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"上田A服务区（惠安方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020901\" num=\"1\" title=\"上田A服务区（惠安方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004005\" domainId=\"0\" id=\"76\" name=\"安溪供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001004005001\" domainId=\"0\" id=\"132\" name=\"安溪供电公司专用充电桩\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004005001\" desc=\"\" domainId=\"0\" id=\"1100001000009\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"安溪建安公园NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100000901\" num=\"1\" title=\"安溪建安公园球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100000902\" num=\"2\" title=\"安溪建安公园车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100000903\" num=\"3\" title=\"安溪建安公园车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100000904\" num=\"4\" title=\"安溪建安公园车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100000905\" num=\"5\" title=\"安溪建安公园车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004005001\" desc=\"\" domainId=\"0\" id=\"1100001000015\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安溪建安公园车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100001501\" num=\"1\" title=\"安溪建安公园车位检测1号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004005001\" desc=\"\" domainId=\"0\" id=\"1100001000018\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安溪建安公园车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100001801\" num=\"1\" title=\"安溪建安公园车位检测2号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004005001\" desc=\"\" domainId=\"0\" id=\"1100001000020\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安溪建安公园车位检测3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100002001\" num=\"1\" title=\"安溪建安公园车位检测3号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004005001\" desc=\"\" domainId=\"0\" id=\"1100001000024\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安溪建安公园车位检测4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100002401\" num=\"1\" title=\"安溪建安公园车位检测4号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004006\" domainId=\"0\" id=\"77\" name=\"惠安供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001004006001\" domainId=\"0\" id=\"133\" name=\"惠安供电公司专用充电桩\" type=\"\">\n" +
                "                    <Device alert=\"7\" alertout=\"0\" channel=\"7\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000247\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7$9004:1,2,3,4,5,6,7$9002:1,2,3,4,5,6,7$9007:1,2,3,4,5,6,7\" title=\"泉州台商投资行政服务中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100024701\" num=\"1\" title=\"泉州台商投资行政服务中心球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100024702\" num=\"2\" title=\"泉州台商投资行政服务中心车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100024703\" num=\"3\" title=\"泉州台商投资行政服务中心车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100024704\" num=\"4\" title=\"泉州台商投资行政服务中心车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100024705\" num=\"5\" title=\"泉州台商投资行政服务中心车位检测4\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100024706\" num=\"6\" title=\"泉州台商投资行政服务中心车位检测5\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100024707\" num=\"7\" title=\"泉州台商投资行政服务中心车位检测6\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000249\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州台商投资行政服务中心车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100024901\" num=\"1\" title=\"泉州台商投资行政服务中心车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000251\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州台商投资行政服务中心车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100025101\" num=\"1\" title=\"泉州台商投资行政服务中心车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000254\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"泉州台商投资行政服务中心车位检测3\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州台商投资行政服务中心车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100025401\" num=\"1\" title=\"泉州台商投资行政服务中心车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000256\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州台商投资行政服务中心车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100025601\" num=\"1\" title=\"泉州台商投资行政服务中心车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000258\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州台商投资行政服务中心车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100025801\" num=\"1\" title=\"泉州台商投资行政服务中心车位检测6\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004006001\" desc=\"\" domainId=\"0\" id=\"1100001000261\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州台商投资行政服务中心车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100026101\" num=\"1\" title=\"泉州台商投资行政服务中心车位检测5\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004007\" domainId=\"0\" id=\"78\" name=\"永春供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001004007001\" domainId=\"0\" id=\"134\" name=\"永春供电公司专用充电桩\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004007001\" desc=\"\" domainId=\"0\" id=\"1100001000269\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"永春旅游集散中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100026901\" num=\"1\" title=\"永春旅游集散中心球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100026902\" num=\"2\" title=\"永春旅游集散中心车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100026903\" num=\"3\" title=\"永春旅游集散中心车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100026904\" num=\"4\" title=\"永春旅游集散中心车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100026905\" num=\"5\" title=\"永春旅游集散中心车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004007001\" desc=\"\" domainId=\"0\" id=\"1100001000272\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永春旅游集散中心车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100027201\" num=\"1\" title=\"永春旅游集散中心车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004007001\" desc=\"\" domainId=\"0\" id=\"1100001000274\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永春旅游集散中心车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100027401\" num=\"1\" title=\"永春旅游集散中心车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004007001\" desc=\"\" domainId=\"0\" id=\"1100001000276\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永春旅游集散中心车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100027601\" num=\"1\" title=\"永春旅游集散中心车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004007001\" desc=\"\" domainId=\"0\" id=\"1100001000278\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永春旅游集散中心车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100027801\" num=\"1\" title=\"永春旅游集散中心车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "                <Department coding=\"1001004007002\" domainId=\"0\" id=\"135\" name=\"永春供电公司城市充电站\" type=\"\"/>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004008\" domainId=\"0\" id=\"79\" name=\"泉州供电公司泉港城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001004009\" domainId=\"0\" id=\"80\" name=\"泉州供电公司清蒙城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004009\" desc=\"\" domainId=\"0\" id=\"1100001000506\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"泉州鲤城浮桥文化中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100050601\" num=\"1\" title=\"泉州鲤城浮桥文化中心气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100050602\" num=\"2\" title=\"泉州鲤城浮桥文化中心车位检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100050603\" num=\"3\" title=\"泉州鲤城浮桥文化中心车位检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100050604\" num=\"4\" title=\"泉州鲤城浮桥文化中心车位检测器3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100050605\" num=\"5\" title=\"泉州鲤城浮桥文化中心车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004009\" desc=\"\" domainId=\"0\" id=\"1100001000510\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州鲤城浮桥文化中心车位检测器1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100051001\" num=\"1\" title=\"泉州鲤城浮桥文化中心车位检测器1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004009\" desc=\"\" domainId=\"0\" id=\"1100001000512\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州鲤城浮桥文化中心车位检测器2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100051201\" num=\"1\" title=\"泉州鲤城浮桥文化中心车位检测器2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004009\" desc=\"\" domainId=\"0\" id=\"1100001000514\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州鲤城浮桥文化中心车位检测器3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100051401\" num=\"1\" title=\"泉州鲤城浮桥文化中心车位检测器3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004009\" desc=\"\" domainId=\"0\" id=\"1100001000518\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州鲤城浮桥文化中心车位检测器4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100051801\" num=\"1\" title=\"泉州鲤城浮桥文化中心车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004010\" domainId=\"0\" id=\"81\" name=\"泉州供电公司洛江城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004010\" desc=\"\" domainId=\"0\" id=\"1100001000551\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"泉州洛江供电公司NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100055101\" num=\"1\" title=\"泉州洛江供电公司气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100055102\" num=\"2\" title=\"泉州洛江供电公司车位检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100055103\" num=\"3\" title=\"泉州洛江供电公司车位检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100055104\" num=\"4\" title=\"泉州洛江供电公司车位检测器3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100055105\" num=\"5\" title=\"泉州洛江供电公司车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004010\" desc=\"\" domainId=\"0\" id=\"1100001000553\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州洛江供电公司车位检测器1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100055301\" num=\"1\" title=\"泉州洛江供电公司车位检测器1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004010\" desc=\"\" domainId=\"0\" id=\"1100001000554\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州洛江供电公司车位检测器2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100055401\" num=\"1\" title=\"泉州洛江供电公司车位检测器2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004010\" desc=\"\" domainId=\"0\" id=\"1100001000560\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州洛江供电公司车位检测器3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100056001\" num=\"1\" title=\"泉州洛江供电公司车位检测器3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004010\" desc=\"\" domainId=\"0\" id=\"1100001000561\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州洛江供电公司车位检测器4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100056101\" num=\"1\" title=\"泉州洛江供电公司车位检测器4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004011\" domainId=\"0\" id=\"82\" name=\"泉州供电公司北门城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001004012\" domainId=\"0\" id=\"83\" name=\"泉州供电公司石狮行政服务中心\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004012\" desc=\"\" domainId=\"0\" id=\"1100001000525\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"石狮行政服务中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100052501\" num=\"1\" title=\"石狮行政服务中心气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052502\" num=\"2\" title=\"石狮行政服务中心车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052503\" num=\"3\" title=\"石狮行政服务中心车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052504\" num=\"4\" title=\"石狮行政服务中心车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052505\" num=\"5\" title=\"石狮行政服务中心车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004012\" desc=\"\" domainId=\"0\" id=\"1100001000527\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮行政服务中心车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100052701\" num=\"1\" title=\"石狮行政服务中心车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004012\" desc=\"\" domainId=\"0\" id=\"1100001000531\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮行政服务中心车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100053101\" num=\"1\" title=\"石狮行政服务中心车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004012\" desc=\"\" domainId=\"0\" id=\"1100001000545\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮行政服务中心车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100054501\" num=\"1\" title=\"石狮行政服务中心车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004012\" desc=\"\" domainId=\"0\" id=\"1100001000547\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮行政服务中心车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100054701\" num=\"1\" title=\"石狮行政服务中心车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004013\" domainId=\"0\" id=\"84\" name=\"泉州丰泽西湖公园充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004013\" desc=\"\" domainId=\"0\" id=\"1100001000476\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"泉州丰泽西湖公园NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100047601\" num=\"1\" title=\"泉州丰泽西湖公园NVR球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100047602\" num=\"2\" title=\"泉州丰泽西湖公园车位检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100047603\" num=\"3\" title=\"泉州丰泽西湖公园车位检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100047604\" num=\"4\" title=\"泉州丰泽西湖公园车位检测器3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100047605\" num=\"5\" title=\"泉州丰泽西湖公园车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004013\" desc=\"\" domainId=\"0\" id=\"1100001000481\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州丰泽西湖公园车位检测器1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100048101\" num=\"1\" title=\"泉州丰泽西湖公园车位检测器1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004013\" desc=\"\" domainId=\"0\" id=\"1100001000482\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州丰泽西湖公园车位检测器2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100048201\" num=\"1\" title=\"泉州丰泽西湖公园车位检测器2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004013\" desc=\"\" domainId=\"0\" id=\"1100001000484\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州丰泽西湖公园车位检测器3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100048401\" num=\"1\" title=\"泉州丰泽西湖公园车位检测器3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001004013\" desc=\"\" domainId=\"0\" id=\"1100001000490\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"泉州丰泽西湖公园车位检测器4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100049001\" num=\"1\" title=\"泉州丰泽西湖公园车位检测器4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" );stringBuilder.append("<Department coding=\"1001004014\" domainId=\"0\" id=\"85\" name=\"泉州供电公司晋江梅岭电力小区\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004014\" desc=\"\" domainId=\"0\" id=\"1100001000465\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"晋江梅岭电力小区NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100046501\" num=\"1\" title=\"晋江梅岭电力小区气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100046502\" num=\"2\" title=\"晋江梅岭电力小区车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100046503\" num=\"3\" title=\"晋江梅岭电力小区车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100046504\" num=\"4\" title=\"晋江梅岭电力小区车位检测13\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100046505\" num=\"5\" title=\"晋江梅岭电力小区车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004014\" desc=\"\" domainId=\"0\" id=\"1100001000468\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州供电公司晋江梅岭电力小区车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100046801\" num=\"1\" title=\"泉州供电公司晋江梅岭电力小区车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004014\" desc=\"\" domainId=\"0\" id=\"1100001000470\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州供电公司晋江梅岭电力小区车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100047001\" num=\"1\" title=\"泉州供电公司晋江梅岭电力小区车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004014\" desc=\"\" domainId=\"0\" id=\"1100001000472\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州供电公司晋江梅岭电力小区车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100047201\" num=\"1\" title=\"泉州供电公司晋江梅岭电力小区车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004014\" desc=\"\" domainId=\"0\" id=\"1100001000474\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"泉州供电公司晋江梅岭电力小区车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100047401\" num=\"1\" title=\"泉州供电公司晋江梅岭电力小区车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004015\" domainId=\"0\" id=\"86\" name=\"石狮供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001004015001\" domainId=\"0\" id=\"136\" name=\"石狮供电公司宝盖山城市充电站\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004015001\" desc=\"\" domainId=\"0\" id=\"1100001000108\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"石狮市宝盖山充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100010801\" num=\"1\" title=\"石狮市宝盖山充电站球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010802\" num=\"2\" title=\"石狮市宝盖山充电站车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010803\" num=\"3\" title=\"石狮市宝盖山充电站车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010804\" num=\"4\" title=\"石狮市宝盖山充电站车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100010805\" num=\"5\" title=\"石狮市宝盖山充电站车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004015001\" desc=\"\" domainId=\"0\" id=\"1100001000130\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮市宝盖山充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100013001\" num=\"1\" title=\"石狮市宝盖山充电站车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004015001\" desc=\"\" domainId=\"0\" id=\"1100001000132\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮市宝盖山充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100013201\" num=\"1\" title=\"石狮市宝盖山充电站车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004015001\" desc=\"\" domainId=\"0\" id=\"1100001000135\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮市宝盖山充电站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100013501\" num=\"1\" title=\"石狮市宝盖山充电站车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004015001\" desc=\"\" domainId=\"0\" id=\"1100001000137\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"石狮市宝盖山充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100013701\" num=\"1\" title=\"石狮市宝盖山充电站车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004016\" domainId=\"0\" id=\"241\" name=\"泉州供电公司南安市政府充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001004016\" desc=\"\" domainId=\"0\" id=\"1100001000566\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"南安市政府NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100056601\" num=\"1\" title=\"南安市政府气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100056602\" num=\"2\" title=\"南安市政府车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100056603\" num=\"3\" title=\"南安市政府车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100056604\" num=\"4\" title=\"南安市政府车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100056605\" num=\"5\" title=\"南安市政府车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004016\" desc=\"\" domainId=\"0\" id=\"1100001000568\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南安市政府车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100056801\" num=\"1\" title=\"南安市政府车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004016\" desc=\"\" domainId=\"0\" id=\"1100001000570\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南安市政府车位2检测\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100057001\" num=\"1\" title=\"南安市政府车位检测\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004016\" desc=\"\" domainId=\"0\" id=\"1100001000575\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南安市政府车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100057501\" num=\"1\" title=\"南安市政府车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001004016\" desc=\"\" domainId=\"0\" id=\"1100001000577\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南安市政府车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100057701\" num=\"1\" title=\"南安市政府车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001004017\" domainId=\"0\" id=\"247\" name=\"泉州晋江梅岭电力小区\" type=\"\"/>\n" +
                "            <Department coding=\"1001004018\" domainId=\"0\" id=\"249\" name=\"泉州洛江供电所站\" type=\"\"/>\n" +
                "        </Department>\n" +
                "        <Department coding=\"1001005\" domainId=\"0\" id=\"6\" name=\"漳州供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001005001\" domainId=\"0\" id=\"113\" name=\"云霄供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001005001001\" domainId=\"0\" id=\"140\" name=\"云霄供电公司体育场充电站\" type=\"\">\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005001001\" desc=\"\" domainId=\"0\" id=\"1100001000533\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云霄县体育场车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100053301\" num=\"1\" title=\"云霄县体育场车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005001001\" desc=\"\" domainId=\"0\" id=\"1100001000535\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云霄县体育场车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100053501\" num=\"1\" title=\"云霄县体育场车位检测11\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005001001\" desc=\"\" domainId=\"0\" id=\"1100001000537\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云霄县体育场车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100053701\" num=\"1\" title=\"云霄县体育场车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005001001\" desc=\"\" domainId=\"0\" id=\"1100001000539\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云霄县体育场车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100053901\" num=\"1\" title=\"云霄县体育场车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001005001001\" desc=\"\" domainId=\"0\" id=\"1100001000541\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"云霄县体育场充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100054101\" num=\"1\" title=\"云霄县体育场球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100054102\" num=\"2\" title=\"云霄县体育场车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100054103\" num=\"3\" title=\"云霄县体育场车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100054104\" num=\"4\" title=\"云霄县体育场车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100054105\" num=\"5\" title=\"云霄县体育场车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001005002\" domainId=\"0\" id=\"158\" name=\"漳州供电公司龙文区政府\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001005002\" desc=\"\" domainId=\"0\" id=\"1100001000001\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"龙文区政府NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100000101\" num=\"1\" title=\"龙文区政府球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100000102\" num=\"2\" title=\"龙文区政府车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100000103\" num=\"3\" title=\"龙文区政府车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100000104\" num=\"4\" title=\"龙文区政府车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100000105\" num=\"5\" title=\"龙文区政府车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005002\" desc=\"\" domainId=\"0\" id=\"1100001000003\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙文区政府车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100000301\" num=\"1\" title=\"龙文区政府车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005002\" desc=\"\" domainId=\"0\" id=\"1100001000005\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙文区政府车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100000501\" num=\"1\" title=\"龙文区政府车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005002\" desc=\"\" domainId=\"0\" id=\"1100001000008\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙文区政府车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100000801\" num=\"1\" title=\"龙文区政府车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005002\" desc=\"\" domainId=\"0\" id=\"1100001000010\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙文区政府车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100001001\" num=\"1\" title=\"龙文区政府车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001005003\" domainId=\"0\" id=\"115\" name=\"漳浦供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001005003001\" domainId=\"0\" id=\"137\" name=\"漳浦供电公司印石公园充电站\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001005003001\" desc=\"\" domainId=\"0\" id=\"1100001000467\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"漳浦县印石花园NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100046701\" num=\"1\" title=\"漳浦县印石花园球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100046702\" num=\"2\" title=\"漳浦县印石花园车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100046703\" num=\"3\" title=\"漳浦县印石花园车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100046704\" num=\"4\" title=\"漳浦县印石花园车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100046705\" num=\"5\" title=\"漳浦县印石花园车位检测\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003001\" desc=\"\" domainId=\"0\" id=\"1100001000469\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县印石花园车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100046901\" num=\"1\" title=\"通道1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003001\" desc=\"\" domainId=\"0\" id=\"1100001000471\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县印石花园车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100047101\" num=\"1\" title=\"漳浦县印石花园车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003001\" desc=\"\" domainId=\"0\" id=\"1100001000475\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县印石花园车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100047501\" num=\"1\" title=\"漳浦县印石花园车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003001\" desc=\"\" domainId=\"0\" id=\"1100001000478\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县印石花园车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100047801\" num=\"1\" title=\"漳浦县印石花园车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "                <Department coding=\"1001005003002\" domainId=\"0\" id=\"138\" name=\"漳浦县黄道周公园充电站\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001005003002\" desc=\"\" domainId=\"0\" id=\"1100001000487\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"漳浦县黄道周公园NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100048701\" num=\"1\" title=\"漳浦县黄道周公园球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100048702\" num=\"2\" title=\"漳浦县黄道周公园车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100048703\" num=\"3\" title=\"漳浦县黄道周公园车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100048704\" num=\"4\" title=\"漳浦县黄道周公园车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100048705\" num=\"5\" title=\"漳浦县黄道周公园车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003002\" desc=\"\" domainId=\"0\" id=\"1100001000489\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县黄道周公园车位检1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100048901\" num=\"1\" title=\"漳浦县黄道周公园车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003002\" desc=\"\" domainId=\"0\" id=\"1100001000491\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县黄道周公园车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100049101\" num=\"1\" title=\"漳浦县黄道周公园车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003002\" desc=\"\" domainId=\"0\" id=\"1100001000493\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县黄道周公园车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100049301\" num=\"1\" title=\"漳浦县黄道周公园车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005003002\" desc=\"\" domainId=\"0\" id=\"1100001000495\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"漳浦县黄道周公园车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100049501\" num=\"1\" title=\"漳浦县黄道周公园车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001005004\" domainId=\"0\" id=\"116\" name=\"漳州供电公司6#城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001005005\" domainId=\"0\" id=\"117\" name=\"漳州供电公司5#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000453\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"长泰桃李春风车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100045301\" num=\"1\" title=\"长泰桃李春风车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"7\" alertout=\"0\" channel=\"7\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000615\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7$9004:1,2,3,4,5,6,7$9002:1,2,3,4,5,6,7$9007:1,2,3,4,5,6,7\" title=\"长泰桃李春风NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061501\" num=\"1\" title=\"长泰桃李春风球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061502\" num=\"2\" title=\"长泰桃李春风车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061503\" num=\"3\" title=\"长泰桃李春风车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061504\" num=\"4\" title=\"长泰桃李春风车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061505\" num=\"5\" title=\"长泰桃李春风车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061506\" num=\"6\" title=\"长泰桃李春风车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061507\" num=\"7\" title=\"长泰桃李春风车位检测6\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000616\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"长泰桃李春风车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061601\" num=\"1\" title=\"长泰桃李春风车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000618\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"长泰桃李春风车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061801\" num=\"1\" title=\"长泰桃李春风车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000620\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"长泰桃李春风车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100062001\" num=\"1\" title=\"长泰桃李春风车位检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000621\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"长泰桃李春风车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100062101\" num=\"1\" title=\"长泰桃李春风车位检测5\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001005005\" desc=\"\" domainId=\"0\" id=\"1100001000622\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"长泰桃李春风车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100062201\" num=\"1\" title=\"长泰桃李春风车位检测6\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>");
        stringBuilder.append(" <Department coding=\"1001005006\" domainId=\"0\" id=\"118\" name=\"诏安供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001005006001\" domainId=\"0\" id=\"139\" name=\"诏安县电子商务服务中心\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001005006001\" desc=\"\" domainId=\"0\" id=\"1100001000387\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"诏安县电子商务服务中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100038701\" num=\"1\" title=\"诏安县电子商务服务中心球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100038702\" num=\"2\" title=\"诏安县电子商务服务中心车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100038703\" num=\"3\" title=\"诏安县电子商务服务中心车测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100038704\" num=\"4\" title=\"诏安县电子商务服务中心车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100038705\" num=\"5\" title=\"诏安县电子商务服务中心车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005006001\" desc=\"\" domainId=\"0\" id=\"1100001000391\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"诏安县电子商务服务中心车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100039101\" num=\"1\" title=\"诏安县电子商务服务中心车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005006001\" desc=\"\" domainId=\"0\" id=\"1100001000393\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"诏安县电子商务服务中心车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100039301\" num=\"1\" title=\"诏安县电子商务服务中心车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005006001\" desc=\"\" domainId=\"0\" id=\"1100001000395\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"诏安县电子商务服务中心车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100039501\" num=\"1\" title=\"诏安县电子商务服务中心车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005006001\" desc=\"\" domainId=\"0\" id=\"1100001000398\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"诏安县电子商务服务中心车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100039801\" num=\"1\" title=\"诏安县电子商务服务中心车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001005007\" domainId=\"0\" id=\"119\" name=\"漳州供电公司龙江运维检修基地\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005007\" desc=\"\" domainId=\"0\" id=\"1100001000436\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙江运维检修基地车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100043601\" num=\"1\" title=\"龙江运维检修基地车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005007\" desc=\"\" domainId=\"0\" id=\"1100001000438\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙江运维检修基地车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100043801\" num=\"1\" title=\"龙江运维检修基地车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005007\" desc=\"\" domainId=\"0\" id=\"1100001000440\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙江运维检修基地车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100044001\" num=\"1\" title=\"龙江运维检修基地车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001005007\" desc=\"\" domainId=\"0\" id=\"1100001000442\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙江运维检修基地车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100044201\" num=\"1\" title=\"龙江运维检修基地车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001005007\" desc=\"\" domainId=\"0\" id=\"1100001000444\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"龙江运维检修基地球机NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100044401\" num=\"1\" title=\"龙江运维检修基地NVR\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044402\" num=\"2\" title=\"龙江运维检修基地车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044403\" num=\"3\" title=\"龙江运维检修基地车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044404\" num=\"4\" title=\"龙江运维检修基地车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100044405\" num=\"5\" title=\"龙江运维检修基地车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "        </Department>\n" +
                "        <Department coding=\"1001006\" domainId=\"0\" id=\"7\" name=\"龙岩供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001006001\" domainId=\"0\" id=\"26\" name=\"上杭城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001006001\" desc=\"\" domainId=\"0\" id=\"1100001000057\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"龙岩上杭江滨公园NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100005701\" num=\"1\" title=\"龙岩上杭江滨公园球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100005702\" num=\"2\" title=\"龙岩上杭江滨公园车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100005703\" num=\"3\" title=\"龙岩上杭江滨公园车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100005704\" num=\"4\" title=\"龙岩上杭江滨公园车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100005705\" num=\"5\" title=\"龙岩上杭江滨公园车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001006001\" desc=\"\" domainId=\"0\" id=\"1100001000062\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙岩上杭江滨公园车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100006201\" num=\"1\" title=\"龙岩上杭江滨公园车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001006001\" desc=\"\" domainId=\"0\" id=\"1100001000064\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙岩上杭江滨公园车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100006401\" num=\"1\" title=\"龙岩上杭江滨公园车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001006001\" desc=\"\" domainId=\"0\" id=\"1100001000066\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙岩上杭江滨公园车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100006601\" num=\"1\" title=\"龙岩上杭江滨公园车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001006001\" desc=\"\" domainId=\"0\" id=\"1100001000070\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"龙岩上杭江滨公园车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100007001\" num=\"1\" title=\"龙岩上杭江滨公园车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001006002\" domainId=\"0\" id=\"27\" name=\"古田景区城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001006003\" domainId=\"0\" id=\"28\" name=\"漳平城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001006003\" desc=\"\" domainId=\"0\" id=\"1100001000178\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"龙岩漳平市医院NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017801\" num=\"1\" title=\"龙岩漳平市医院球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100017802\" num=\"2\" title=\"龙岩漳平市医院车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100017803\" num=\"3\" title=\"龙岩漳平市医院车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100017804\" num=\"4\" title=\"龙岩漳平市医院车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100017805\" num=\"5\" title=\"龙岩漳平市医院车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006003\" desc=\"\" domainId=\"0\" id=\"1100001000182\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩漳平市医院车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100018201\" num=\"1\" title=\"龙岩漳平市医院车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006003\" desc=\"\" domainId=\"0\" id=\"1100001000186\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩漳平市医院车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100018601\" num=\"1\" title=\"龙岩漳平市医院车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006003\" desc=\"\" domainId=\"0\" id=\"1100001000191\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩漳平市医院车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100019101\" num=\"1\" title=\"龙岩漳平市医院车位检测3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006003\" desc=\"\" domainId=\"0\" id=\"1100001000192\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩漳平市医院车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100019201\" num=\"1\" title=\"龙岩漳平市医院车位检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001006004\" domainId=\"0\" id=\"29\" name=\"永定城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001006004\" desc=\"\" domainId=\"0\" id=\"1100001000033\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"龙岩市永定客家古镇NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100003301\" num=\"1\" title=\"龙岩市永定客家古镇球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100003302\" num=\"2\" title=\"龙岩市永定客家古镇车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100003303\" num=\"3\" title=\"龙岩市永定客家古镇车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100003304\" num=\"4\" title=\"龙岩市永定客家古镇车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100003305\" num=\"5\" title=\"龙岩市永定客家古镇车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006004\" desc=\"\" domainId=\"0\" id=\"1100001000036\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩市永定客家古镇车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100003601\" num=\"1\" title=\"龙岩市永定客家古镇车位检测1\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006004\" desc=\"\" domainId=\"0\" id=\"1100001000038\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩市永定客家古镇车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100003801\" num=\"1\" title=\"龙岩市永定客家古镇车位检测2\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006004\" desc=\"\" domainId=\"0\" id=\"1100001000040\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩市永定客家古镇车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100004001\" num=\"1\" title=\"龙岩市永定客家古镇车位检测3\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001006004\" desc=\"\" domainId=\"0\" id=\"1100001000042\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"龙岩市永定客家古镇车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100004201\" num=\"1\" title=\"龙岩市永定客家古镇车位检测4\" type=\"0\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001006005\" domainId=\"0\" id=\"30\" name=\"永定土楼景区城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001006006\" domainId=\"0\" id=\"31\" name=\"武平城市充电站\" type=\"\"/>\n" +
                "        </Department>\n");stringBuilder.append("<Department coding=\"1001007\" domainId=\"0\" id=\"8\" name=\"三明供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001007001\" domainId=\"0\" id=\"88\" name=\"三明供电公司体育馆城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001007002\" domainId=\"0\" id=\"89\" name=\"三明供电公司梅列城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001007003\" domainId=\"0\" id=\"90\" name=\"三明供电公司海西生态游乐园城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001007004\" domainId=\"0\" id=\"91\" name=\"三明供电公司城关城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001007005\" domainId=\"0\" id=\"92\" name=\"永安供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001007005001\" domainId=\"0\" id=\"141\" name=\"永安供电公司城市充电站\" type=\"\"/>\n" +
                "                <Department coding=\"1001007005002\" domainId=\"0\" id=\"189\" name=\"永安埔岭汽车工业园区充电站\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001007005002\" desc=\"\" domainId=\"0\" id=\"1100001000418\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"永安埔岭汽车工业园区NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100041801\" num=\"1\" title=\"永安埔岭汽车工业园区球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041802\" num=\"2\" title=\"埔岭汽车工业园区车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041803\" num=\"3\" title=\"埔岭汽车工业园区车位检测4\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041804\" num=\"4\" title=\"埔岭汽车工业园区车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100041805\" num=\"5\" title=\"埔岭汽车工业园区车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007005002\" desc=\"\" domainId=\"0\" id=\"1100001000420\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永安埔岭汽车工业园区车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042001\" num=\"1\" title=\"埔岭汽车工业园区车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007005002\" desc=\"\" domainId=\"0\" id=\"1100001000422\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永安埔岭汽车工业园区车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042201\" num=\"1\" title=\"埔岭汽车工业园区车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007005002\" desc=\"\" domainId=\"0\" id=\"1100001000425\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永安埔岭汽车工业园区车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042501\" num=\"1\" title=\"埔岭汽车工业园区车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007005002\" desc=\"\" domainId=\"0\" id=\"1100001000427\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"永安埔岭汽车工业园区车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100042701\" num=\"1\" title=\"埔岭汽车工业园区车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001007006\" domainId=\"0\" id=\"93\" name=\"尤溪供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001007006001\" domainId=\"0\" id=\"142\" name=\"尤溪供电公司城市充电站\" type=\"\"/>\n" +
                "                <Department coding=\"1001007006002\" domainId=\"0\" id=\"143\" name=\"尤溪供电公司公交车充电站\" type=\"\">\n" +
                "                    <Device alert=\"7\" alertout=\"0\" channel=\"7\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000500\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7$9004:1,2,3,4,5,6,7$9002:1,2,3,4,5,6,7$9007:1,2,3,4,5,6,7\" title=\"尤溪供电公司公交车充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100050001\" num=\"1\" title=\"尤溪供电公司公交车球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100050002\" num=\"2\" title=\"尤溪供电公司公交车洋中车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100050003\" num=\"3\" title=\"尤溪供电公司公交车洋中车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100050004\" num=\"4\" title=\"尤溪供电公司公交车洋中车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100050005\" num=\"5\" title=\"尤溪供电公司公交车洋中车位检测4\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100050006\" num=\"6\" title=\"尤溪供电公司公交车洋中车位检测5\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100050007\" num=\"7\" title=\"尤溪供电公司公交车洋中车位检测6\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000503\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"尤溪供电公司公交车洋中车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100050301\" num=\"1\" title=\"尤溪供电公司公交车洋中车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000507\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"尤溪供电公司公交车洋中车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100050701\" num=\"1\" title=\"尤溪供电公司公交车洋中车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000509\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"尤溪供电公司公交车洋中车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100050901\" num=\"1\" title=\"尤溪供电公司公交车洋中车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000511\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"尤溪供电公司公交车洋中车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100051101\" num=\"1\" title=\"尤溪供电公司公交车洋中车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000513\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"尤溪供电公司公交车洋中车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100051301\" num=\"1\" title=\"尤溪供电公司公交车洋中车位检测5\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007006002\" desc=\"\" domainId=\"0\" id=\"1100001000516\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"尤溪供电公司公交车洋中车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100051601\" num=\"1\" title=\"尤溪供电公司公交车洋中车位检测6\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001007007\" domainId=\"0\" id=\"94\" name=\"三明供电公司城关公交车充电站\" type=\"\">\n" +
                "                <Device alert=\"4\" alertout=\"0\" channel=\"4\" coding=\"1001007007\" desc=\"\" domainId=\"0\" id=\"1100001000529\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4$9004:1,2,3,4$9002:1,2,3,4$9007:1,2,3,4\" title=\"三明供电公司城关公交车三明学院NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100052901\" num=\"1\" title=\"三明供电公司城关公交车三明学院球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052902\" num=\"2\" title=\"三明供电公司城关公交车三明学院车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052903\" num=\"3\" title=\"三明供电公司城关公交车三明学院车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100052904\" num=\"4\" title=\"三明供电公司城关公交车三明学院车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007007\" desc=\"\" domainId=\"0\" id=\"1100001000544\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"三明供电公司城关三明学院车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100054401\" num=\"1\" title=\"三明供电公司城关三明学院车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007007\" desc=\"\" domainId=\"0\" id=\"1100001000546\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"三明供电公司城关三明学院车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100054601\" num=\"1\" title=\"三明供电公司城关三明学院车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007007\" desc=\"\" domainId=\"0\" id=\"1100001000548\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"三明供电公司城关三明学院车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100054801\" num=\"1\" title=\"三明供电公司城关三明学院车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001007008\" domainId=\"0\" id=\"95\" name=\"建泰高速闽江源服务区（建宁方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"3\" alertout=\"0\" channel=\"3\" coding=\"1001007008\" desc=\"\" domainId=\"0\" id=\"1100001000195\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3$9004:1,2,3$9002:1,2,3$9007:1,2,3\" title=\"闽江源服务区（建宁方向）NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100019501\" num=\"1\" title=\"闽江源服务区（建宁方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100019502\" num=\"2\" title=\"闽江源服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100019503\" num=\"3\" title=\"闽江源服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007008\" desc=\"\" domainId=\"0\" id=\"1100001000199\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽江源服务区（建宁方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100019901\" num=\"1\" title=\"闽江源服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007008\" desc=\"\" domainId=\"0\" id=\"1100001000201\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽江源服务区（建宁方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020101\" num=\"1\" title=\"闽江源服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001007009\" domainId=\"0\" id=\"96\" name=\"建泰高速闽江源服务区（泰宁方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007009\" desc=\"\" domainId=\"0\" id=\"1100001000204\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽江源服务区（泰宁方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020401\" num=\"1\" title=\"闽江源服务区（泰宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007009\" desc=\"\" domainId=\"0\" id=\"1100001000208\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"闽江源服务区（泰宁方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100020801\" num=\"1\" title=\"闽江源服务区（泰宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"3\" alertout=\"0\" channel=\"3\" coding=\"1001007009\" desc=\"\" domainId=\"0\" id=\"1100001000264\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3$9004:1,2,3$9002:1,2,3$9007:1,2,3\" title=\"闽江源服务区（泰宁方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100026401\" num=\"1\" title=\"闽江源服务区（泰宁方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100026402\" num=\"2\" title=\"闽江源服务区（泰宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100026403\" num=\"3\" title=\"闽江源服务区（泰宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001007010\" domainId=\"0\" id=\"97\" name=\"将乐供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001007010001\" domainId=\"0\" id=\"144\" name=\"将乐供电公司公交车充电站\" type=\"\">\n" +
                "                    <Device alert=\"7\" alertout=\"0\" channel=\"7\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000552\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7$9004:1,2,3,4,5,6,7$9002:1,2,3,4,5,6,7$9007:1,2,3,4,5,6,7\" title=\"将乐供电公司公交车玉华洞风景区NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100055201\" num=\"1\" title=\"将乐供电公司公交车玉华洞风景区球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100055202\" num=\"2\" title=\"将乐供电公司公交车玉华洞风景区车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100055203\" num=\"3\" title=\"将乐供电公司公交车玉华洞风景区车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100055204\" num=\"4\" title=\"将乐供电公司公交车玉华洞风景区车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100055205\" num=\"5\" title=\"将乐供电公司公交车玉华洞风景区车位检测4\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100055206\" num=\"6\" title=\"将乐供电公司公交车玉华洞风景区车位检测5\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100055207\" num=\"7\" title=\"将乐供电公司公交车玉华洞风景区车位检测6\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000555\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐供电公司玉华洞风景区车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100055501\" num=\"1\" title=\"将乐供电公司玉华洞风景区车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000557\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐供电公司玉华洞风景区车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100055701\" num=\"1\" title=\"将乐供电公司玉华洞风景区车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000559\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐供电公司玉华洞风景区车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100055901\" num=\"1\" title=\"将乐供电公司玉华洞风景区车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000562\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐供电公司玉华洞风景区车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100056201\" num=\"1\" title=\"将乐供电公司玉华洞风景区车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000564\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐供电公司玉华洞风景区车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100056401\" num=\"1\" title=\"将乐供电公司玉华洞风景区车位检测5\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010001\" desc=\"\" domainId=\"0\" id=\"1100001000569\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐供电公司玉华洞风景区车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100056901\" num=\"1\" title=\"将乐供电公司玉华洞风景区车位检测6\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n");stringBuilder.append("<Department coding=\"1001007010002\" domainId=\"0\" id=\"145\" name=\"将乐供电公司专用充电桩\" type=\"\"/>\n" +
                "                <Department coding=\"1001007010003\" domainId=\"0\" id=\"175\" name=\"将乐供电公司金森大厦\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001007010003\" desc=\"\" domainId=\"0\" id=\"1100001000572\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"将乐供电公司金森大厦停车场NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100057201\" num=\"1\" title=\"将乐供电公司金森大厦停车场球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100057202\" num=\"2\" title=\"将乐供电公司金森大厦停车场车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100057203\" num=\"3\" title=\"将乐供电公司金森大厦停车场车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100057204\" num=\"4\" title=\"将乐供电公司金森大厦停车场车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100057205\" num=\"5\" title=\"将乐供电公司金森大厦停车场车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010003\" desc=\"\" domainId=\"0\" id=\"1100001000574\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐金森大厦停车场车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100057401\" num=\"1\" title=\"将乐金森大厦停车场车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010003\" desc=\"\" domainId=\"0\" id=\"1100001000576\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐金森大厦停车场车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100057601\" num=\"1\" title=\"将乐金森大厦停车场车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010003\" desc=\"\" domainId=\"0\" id=\"1100001000578\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐金森大厦停车场车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100057801\" num=\"1\" title=\"将乐金森大厦停车场车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001007010003\" desc=\"\" domainId=\"0\" id=\"1100001000583\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"将乐金森大厦停车场车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100058301\" num=\"1\" title=\"将乐金森大厦停车场车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001007013\" domainId=\"0\" id=\"100\" name=\"泰宁供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001007013001\" domainId=\"0\" id=\"146\" name=\"泰宁供电公司公交车充电站\" type=\"\"/>\n" +
                "            </Department>\n" +
                "        </Department>\n" +
                "        <Department coding=\"1001008\" domainId=\"0\" id=\"9\" name=\"南平供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001008001\" domainId=\"0\" id=\"32\" name=\"浦建高速大庄服务区（浦城方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008001\" desc=\"\" domainId=\"0\" id=\"1100001000145\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"大庄服务区（浦城方向）NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100014501\" num=\"1\" title=\"大庄服务区（浦城方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014502\" num=\"2\" title=\"大庄服务区（浦城方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014503\" num=\"3\" title=\"大庄服务区（浦城方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014504\" num=\"4\" title=\"大庄服务区（浦城方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100014505\" num=\"5\" title=\"大庄服务区（浦城方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008001\" desc=\"\" domainId=\"0\" id=\"1100001000147\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（浦城方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100014701\" num=\"1\" title=\"大庄服务区（浦城方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008001\" desc=\"\" domainId=\"0\" id=\"1100001000149\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（浦城方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100014901\" num=\"1\" title=\"大庄服务区（浦城方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008001\" desc=\"\" domainId=\"0\" id=\"1100001000151\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（浦城方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015101\" num=\"1\" title=\"大庄服务区（浦城方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008001\" desc=\"\" domainId=\"0\" id=\"1100001000153\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（浦城方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015301\" num=\"1\" title=\"大庄服务区（浦城方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" );stringBuilder.append(" <Department coding=\"1001008002\" domainId=\"0\" id=\"33\" name=\"浦建高速大庄服务区（建宁方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008002\" desc=\"\" domainId=\"0\" id=\"1100001000330\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"大庄服务区（建宁方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100033001\" num=\"1\" title=\"大庄服务区（建宁方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100033002\" num=\"2\" title=\"大庄服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100033003\" num=\"3\" title=\"大庄服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100033004\" num=\"4\" title=\"大庄服务区（建宁方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100033005\" num=\"5\" title=\"大庄服务区（建宁方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008002\" desc=\"\" domainId=\"0\" id=\"1100001000334\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（建宁方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100033401\" num=\"1\" title=\"大庄服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008002\" desc=\"\" domainId=\"0\" id=\"1100001000336\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（建宁方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100033601\" num=\"1\" title=\"大庄服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008002\" desc=\"\" domainId=\"0\" id=\"1100001000339\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（建宁方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100033901\" num=\"1\" title=\"大庄服务区（建宁方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008002\" desc=\"\" domainId=\"0\" id=\"1100001000341\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大庄服务区（建宁方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100034101\" num=\"1\" title=\"大庄服务区（建宁方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008003\" domainId=\"0\" id=\"34\" name=\"浦建高速大埠岗服务区（浦城方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008003\" desc=\"\" domainId=\"0\" id=\"1100001000270\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"大埠岗服务区（浦城方向）NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100027001\" num=\"1\" title=\"大埠岗服务区（浦城方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100027002\" num=\"2\" title=\"大埠岗服务区（浦城方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100027003\" num=\"3\" title=\"大埠岗服务区（浦城方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100027004\" num=\"4\" title=\"大埠岗服务区（浦城方向）检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100027005\" num=\"5\" title=\"大埠岗服务区（浦城方向）检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008003\" desc=\"\" domainId=\"0\" id=\"1100001000277\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（浦城方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100027701\" num=\"1\" title=\"大埠岗服务区（浦城方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008003\" desc=\"\" domainId=\"0\" id=\"1100001000279\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（浦城方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100027901\" num=\"1\" title=\"大埠岗服务区（浦城方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008003\" desc=\"\" domainId=\"0\" id=\"1100001000281\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（浦城方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028101\" num=\"1\" title=\"大埠岗服务区（浦城方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008003\" desc=\"\" domainId=\"0\" id=\"1100001000286\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（浦城方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100028601\" num=\"1\" title=\"大埠岗服务区（浦城方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008004\" domainId=\"0\" id=\"35\" name=\"浦建高速大埠岗服务区（建宁方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008004\" desc=\"\" domainId=\"0\" id=\"1100001000346\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"大埠岗服务区（建宁方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100034601\" num=\"1\" title=\"大埠岗服务区（建宁方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100034602\" num=\"2\" title=\"大埠岗服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100034603\" num=\"3\" title=\"大埠岗服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100034604\" num=\"4\" title=\"大埠岗服务区（建宁方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100034605\" num=\"5\" title=\"大埠岗服务区（建宁方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008004\" desc=\"\" domainId=\"0\" id=\"1100001000348\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（建宁方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100034801\" num=\"1\" title=\"大埠岗服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008004\" desc=\"\" domainId=\"0\" id=\"1100001000350\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（建宁方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035001\" num=\"1\" title=\"大埠岗服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008004\" desc=\"\" domainId=\"0\" id=\"1100001000352\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（建宁方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035201\" num=\"1\" title=\"大埠岗服务区（建宁方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008004\" desc=\"\" domainId=\"0\" id=\"1100001000354\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大埠岗服务区（建宁方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035401\" num=\"1\" title=\"大埠岗服务区（建宁方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008005\" domainId=\"0\" id=\"36\" name=\"浦建高速大白服务区（浦城方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008005\" desc=\"\" domainId=\"0\" id=\"1100001000357\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"大白服务区（浦城方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035701\" num=\"1\" title=\"大白服务区（浦城方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035702\" num=\"2\" title=\"大白服务区（浦城方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035703\" num=\"3\" title=\"大白服务区（浦城方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035704\" num=\"4\" title=\"大白服务区（浦城方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100035705\" num=\"5\" title=\"大白服务区（浦城方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008005\" desc=\"\" domainId=\"0\" id=\"1100001000359\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（浦城方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035901\" num=\"1\" title=\"大白服务区（浦城方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008005\" desc=\"\" domainId=\"0\" id=\"1100001000361\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（浦城方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036101\" num=\"1\" title=\"大白服务区（浦城方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008005\" desc=\"\" domainId=\"0\" id=\"1100001000365\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（浦城方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036501\" num=\"1\" title=\"大白服务区（浦城方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008005\" desc=\"\" domainId=\"0\" id=\"1100001000367\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（浦城方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036701\" num=\"1\" title=\"大白服务区（浦城方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008006\" domainId=\"0\" id=\"37\" name=\"浦建高速大白服务区（建宁方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008006\" desc=\"\" domainId=\"0\" id=\"1100001000370\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"大白服务区（建宁方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100037001\" num=\"1\" title=\"大白服务区（建宁方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100037002\" num=\"2\" title=\"大白服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100037003\" num=\"3\" title=\"大白服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100037004\" num=\"4\" title=\"大白服务区（建宁方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100037005\" num=\"5\" title=\"大白服务区（建宁方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008006\" desc=\"\" domainId=\"0\" id=\"1100001000372\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（建宁方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100037201\" num=\"1\" title=\"大白服务区（建宁方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008006\" desc=\"\" domainId=\"0\" id=\"1100001000374\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（建宁方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100037401\" num=\"1\" title=\"大白服务区（建宁方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008006\" desc=\"\" domainId=\"0\" id=\"1100001000377\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（建宁方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100037701\" num=\"1\" title=\"大白服务区（建宁方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008006\" desc=\"\" domainId=\"0\" id=\"1100001000379\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"大白服务区（建宁方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100037901\" num=\"1\" title=\"大白服务区（建宁方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008007\" domainId=\"0\" id=\"38\" name=\"宁光高速云灵山服务区（宁德方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008007\" desc=\"\" domainId=\"0\" id=\"1100001000315\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"云灵山服务区（宁德方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100031501\" num=\"1\" title=\"云灵山服务区（宁德方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100031502\" num=\"2\" title=\"云灵山服务区（宁德方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100031503\" num=\"3\" title=\"云灵山服务区（宁德方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100031504\" num=\"4\" title=\"云灵山服务区（宁德方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100031505\" num=\"5\" title=\"云灵山服务区（宁德方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008007\" desc=\"\" domainId=\"0\" id=\"1100001000322\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（宁德方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100032201\" num=\"1\" title=\"云灵山服务区（宁德方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008007\" desc=\"\" domainId=\"0\" id=\"1100001000324\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（宁德方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100032401\" num=\"1\" title=\"云灵山服务区（宁德方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008007\" desc=\"\" domainId=\"0\" id=\"1100001000326\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（宁德方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100032601\" num=\"1\" title=\"云灵山服务区（宁德方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008007\" desc=\"\" domainId=\"0\" id=\"1100001000328\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（宁德方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100032801\" num=\"1\" title=\"云灵山服务区（宁德方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n");stringBuilder.append("<Department coding=\"1001008008\" domainId=\"0\" id=\"39\" name=\"宁光高速云灵山服务区（光泽方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008008\" desc=\"\" domainId=\"0\" id=\"1100001000290\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"云灵山服务区（光泽方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100029001\" num=\"1\" title=\"云灵山服务区（光泽方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029002\" num=\"2\" title=\"云灵山服务区（光泽方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029003\" num=\"3\" title=\"云灵山服务区（光泽方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029004\" num=\"4\" title=\"云灵山服务区（光泽方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100029005\" num=\"5\" title=\"云灵山服务区（光泽方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008008\" desc=\"\" domainId=\"0\" id=\"1100001000294\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（光泽方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100029401\" num=\"1\" title=\"云灵山服务区（光泽方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008008\" desc=\"\" domainId=\"0\" id=\"1100001000297\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（光泽方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100029701\" num=\"1\" title=\"云灵山服务区（光泽方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008008\" desc=\"\" domainId=\"0\" id=\"1100001000301\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（光泽方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100030101\" num=\"1\" title=\"云灵山服务区（光泽方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008008\" desc=\"\" domainId=\"0\" id=\"1100001000308\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"云灵山服务区（光泽方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100030801\" num=\"1\" title=\"云灵山服务区（光泽方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n");stringBuilder.append("<Department coding=\"1001008009\" domainId=\"0\" id=\"40\" name=\"宁光高速安窠服务区（宁德方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008009\" desc=\"\" domainId=\"0\" id=\"1100001000600\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"安窠服务区（宁德方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100060001\" num=\"1\" title=\"安窠服务区（宁德方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060002\" num=\"2\" title=\"安窠服务区（宁德方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060003\" num=\"3\" title=\"安窠服务区（宁德方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060004\" num=\"4\" title=\"安窠服务区（宁德方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060005\" num=\"5\" title=\"安窠服务区（宁德方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008009\" desc=\"\" domainId=\"0\" id=\"1100001000604\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（宁德方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100060401\" num=\"1\" title=\"安窠服务区（宁德方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008009\" desc=\"\" domainId=\"0\" id=\"1100001000606\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（宁德方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100060601\" num=\"1\" title=\"安窠服务区（宁德方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008009\" desc=\"\" domainId=\"0\" id=\"1100001000608\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（宁德方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100060801\" num=\"1\" title=\"安窠服务区（宁德方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008009\" desc=\"\" domainId=\"0\" id=\"1100001000610\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（宁德方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061001\" num=\"1\" title=\"安窠服务区（宁德方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>");
        stringBuilder.append(" <Department coding=\"1001008010\" domainId=\"0\" id=\"41\" name=\"宁光高速安窠服务区（光泽方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008010\" desc=\"\" domainId=\"0\" id=\"1100001000586\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"安窠服务区（光泽方向）NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100058601\" num=\"1\" title=\"安窠服务区（光泽方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100058602\" num=\"2\" title=\"安窠服务区（光泽方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100058603\" num=\"3\" title=\"安窠服务区（光泽方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100058604\" num=\"4\" title=\"安窠服务区（光泽方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100058605\" num=\"5\" title=\"安窠服务区（光泽方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008010\" desc=\"\" domainId=\"0\" id=\"1100001000589\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（光泽方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100058901\" num=\"1\" title=\"安窠服务区（光泽方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008010\" desc=\"\" domainId=\"0\" id=\"1100001000591\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（光泽方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059101\" num=\"1\" title=\"安窠服务区（光泽方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008010\" desc=\"\" domainId=\"0\" id=\"1100001000593\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（光泽方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059301\" num=\"1\" title=\"安窠服务区（光泽方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008010\" desc=\"\" domainId=\"0\" id=\"1100001000595\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"安窠服务区（光泽方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059501\" num=\"1\" title=\"安窠服务区（光泽方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008011\" domainId=\"0\" id=\"120\" name=\"福银高速朱洋服务区（银川方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"4\" alertout=\"0\" channel=\"4\" coding=\"1001008011\" desc=\"\" domainId=\"0\" id=\"1100001000180\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4$9004:1,2,3,4$9002:1,2,3,4$9007:1,2,3,4\" title=\"朱洋服务区（银川方向）NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100018001\" num=\"1\" title=\"朱洋服务区（银川方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100018002\" num=\"2\" title=\"朱洋服务区（银川方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100018003\" num=\"3\" title=\"朱洋服务区（银川方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100018004\" num=\"4\" title=\"朱洋服务区（银川方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008011\" desc=\"\" domainId=\"0\" id=\"1100001000183\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（银川方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100018301\" num=\"1\" title=\"朱洋服务区（银川方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008011\" desc=\"\" domainId=\"0\" id=\"1100001000185\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（银川方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100018501\" num=\"1\" title=\"朱洋服务区（银川方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008011\" desc=\"\" domainId=\"0\" id=\"1100001000187\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（银川方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100018701\" num=\"1\" title=\"朱洋服务区（银川方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008012\" domainId=\"0\" id=\"44\" name=\"福银高速朱洋服务区（福州方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008012\" desc=\"\" domainId=\"0\" id=\"1100001000159\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"朱洋服务区（福州方向）NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015901\" num=\"1\" title=\"朱洋服务区（福州方向）球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015902\" num=\"2\" title=\"朱洋服务区（福州方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015903\" num=\"3\" title=\"朱洋服务区（福州方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015904\" num=\"4\" title=\"朱洋服务区（福州方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015905\" num=\"5\" title=\"朱洋服务区（福州方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008012\" desc=\"\" domainId=\"0\" id=\"1100001000165\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（福州方向）车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100016501\" num=\"1\" title=\"朱洋服务区（福州方向）车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008012\" desc=\"\" domainId=\"0\" id=\"1100001000167\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（福州方向）车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100016701\" num=\"1\" title=\"朱洋服务区（福州方向）车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008012\" desc=\"\" domainId=\"0\" id=\"1100001000169\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（福州方向）车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100016901\" num=\"1\" title=\"朱洋服务区（福州方向）车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008012\" desc=\"\" domainId=\"0\" id=\"1100001000175\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"朱洋服务区（福州方向）车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017501\" num=\"1\" title=\"朱洋服务区（福州方向）车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008013\" domainId=\"0\" id=\"45\" name=\"邵武供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001008013002\" domainId=\"0\" id=\"148\" name=\"邵武供电公司猴子山公交车充电站\" type=\"\"/>\n" +
                "                <Department coding=\"1001008013003\" domainId=\"0\" id=\"149\" name=\"邵武供电公司城南大道城市充电站\" type=\"\">\n" +
                "                    <Device alert=\"0\" alertout=\"0\" channel=\"5\" coding=\"1001008013003\" desc=\"\" domainId=\"0\" id=\"1100001000656\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5\" title=\"邵武和平古镇NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100065601\" num=\"1\" title=\"邵武和平古镇球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100065602\" num=\"2\" title=\"邵武和平古镇车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100065603\" num=\"3\" title=\"邵武和平古镇车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100065604\" num=\"4\" title=\"邵武和平古镇车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100065605\" num=\"5\" title=\"邵武和平古镇车位检测4\" type=\"0\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001008013003\" desc=\"\" domainId=\"0\" id=\"1100001000658\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"邵武和平古镇车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100065801\" num=\"1\" title=\"邵武和平古镇车位检测1\" type=\"0\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001008013003\" desc=\"\" domainId=\"0\" id=\"1100001000660\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"邵武和平古镇车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100066001\" num=\"1\" title=\"邵武和平古镇车位检测2\" type=\"0\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001008013003\" desc=\"\" domainId=\"0\" id=\"1100001000662\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"邵武和平古镇车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100066201\" num=\"1\" title=\"邵武和平古镇车位检测3\" type=\"0\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"0\" alertout=\"0\" channel=\"1\" coding=\"1001008013003\" desc=\"\" domainId=\"0\" id=\"1100001000664\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1\" title=\"邵武和平古镇车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100066401\" num=\"1\" title=\"邵武和平古镇车位检测4\" type=\"0\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" );stringBuilder.append(" <Department coding=\"1001008014\" domainId=\"0\" id=\"161\" name=\"南平北公交站充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000358\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100035801\" num=\"1\" title=\"南平北公交车站充电站车位检测6\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000360\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036001\" num=\"1\" title=\"南平北公交车站充电站车位检测7\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000362\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036201\" num=\"1\" title=\"南平北公交车站充电站车位检测8\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000364\" ip=\"192.168.1.19\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测9\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036401\" num=\"1\" title=\"南平北公交车站充电站车位检测9\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000366\" ip=\"192.168.1.20\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测10\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100036601\" num=\"1\" title=\"南平北公交车站充电站车位检测10\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"12\" alertout=\"0\" channel=\"12\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000624\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10,11,12$9004:1,2,3,4,5,6,7,8,9,10,11,12$9002:1,2,3,4,5,6,7,8,9,10,11,12$9007:1,2,3,4,5,6,7,8,9,10,11,12\" title=\"南平北公交站充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100062401\" num=\"1\" title=\"南平北公交站充电站球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062402\" num=\"2\" title=\"南平北公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062403\" num=\"3\" title=\"南平北公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062404\" num=\"4\" title=\"南平北公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062405\" num=\"5\" title=\"南平北公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062406\" num=\"6\" title=\"南平北公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062407\" num=\"7\" title=\"南平北公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062408\" num=\"8\" title=\"南平北公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062409\" num=\"9\" title=\"南平北公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062410\" num=\"10\" title=\"南平北公交站车位检测9\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062411\" num=\"11\" title=\"南平北公交站车位检测10\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100062412\" num=\"12\" title=\"南平北公交站充电站2号360球机\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"11\" title=\"报警输入11\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"12\" title=\"报警输入12\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000631\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100063101\" num=\"1\" title=\"南平北公交车站充电站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000633\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100063301\" num=\"1\" title=\"南平北公交车站充电站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000635\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100063501\" num=\"1\" title=\"南平北公交车站充电站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000640\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100064001\" num=\"1\" title=\"南平北公交车站充电站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008014\" desc=\"\" domainId=\"0\" id=\"1100001000642\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平北公交车站充电站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100064201\" num=\"1\" title=\"南平北公交车站充电站车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008015\" domainId=\"0\" id=\"180\" name=\"南平水溪口公交站充电站\" type=\"\">\n" +
                "                <Device alert=\"12\" alertout=\"0\" channel=\"12\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000388\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5,6,7,8,9,10,11,12$9004:1,2,3,4,5,6,7,8,9,10,11,12$9002:1,2,3,4,5,6,7,8,9,10,11,12$9007:1,2,3,4,5,6,7,8,9,10,11,12\" title=\"南平水溪口公交站充电站NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100038801\" num=\"1\" title=\"南平水溪口公交站球机1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038802\" num=\"2\" title=\"南平水溪口公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038803\" num=\"3\" title=\"南平水溪口公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038804\" num=\"4\" title=\"南平水溪口公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038805\" num=\"5\" title=\"南平水溪口公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038806\" num=\"6\" title=\"南平水溪口公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038807\" num=\"7\" title=\"南平水溪口公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038808\" num=\"8\" title=\"南平水溪口公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038809\" num=\"9\" title=\"南平水溪口公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038810\" num=\"10\" title=\"南平水溪口公交站车位检测9\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038811\" num=\"11\" title=\"南平水溪口公交站车位检测10\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100038812\" num=\"12\" title=\"南平水溪口公交站球机2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"6\" title=\"报警输入6\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"7\" title=\"报警输入7\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"8\" title=\"报警输入8\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"9\" title=\"报警输入9\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"10\" title=\"报警输入10\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"11\" title=\"报警输入11\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"12\" title=\"报警输入12\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000390\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100039001\" num=\"1\" title=\"南平水溪口公交站车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000392\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100039201\" num=\"1\" title=\"南平水溪口公交站车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000394\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100039401\" num=\"1\" title=\"南平水溪口公交站车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000396\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100039601\" num=\"1\" title=\"南平水溪口公交站车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000401\" ip=\"192.168.1.15\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测5\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040101\" num=\"1\" title=\"南平水溪口公交站车位检测5\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000404\" ip=\"192.168.1.16\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测6\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040401\" num=\"1\" title=\"南平水溪口公交站车位检测6\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000406\" ip=\"192.168.1.17\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测7\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100040601\" num=\"1\" title=\"南平水溪口公交站车位检测7\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000410\" ip=\"192.168.1.18\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测8\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100041001\" num=\"1\" title=\"南平水溪口公交站车位检测8\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000412\" ip=\"192.168.1.20\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测10\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100041201\" num=\"1\" title=\"南平水溪口公交站车位检测10\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008015\" desc=\"\" domainId=\"0\" id=\"1100001000416\" ip=\"192.168.1.19\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"南平水溪口公交站车位检测9\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100041601\" num=\"1\" title=\"南平水溪口公交站车位检测9\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001008016\" domainId=\"0\" id=\"48\" name=\"武夷山供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001008016001\" domainId=\"0\" id=\"150\" name=\"武夷山供电公司公馆城市充电站\" type=\"\">\n" +
                "                    <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001008016001\" desc=\"\" domainId=\"0\" id=\"1100001000368\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"武夷山公馆NVR球机\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100036801\" num=\"1\" title=\"武夷山公馆球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100036802\" num=\"2\" title=\"武夷山公馆车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100036803\" num=\"3\" title=\"武夷山公馆车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100036804\" num=\"4\" title=\"武夷山公馆车位检测3\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100036805\" num=\"5\" title=\"武夷山公馆车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008016001\" desc=\"\" domainId=\"0\" id=\"1100001000371\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"武夷山公馆车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100037101\" num=\"1\" title=\"武夷山公馆车位检测1\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008016001\" desc=\"\" domainId=\"0\" id=\"1100001000373\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"武夷山公馆车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100037301\" num=\"1\" title=\"武夷山公馆车位检测2\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008016001\" desc=\"\" domainId=\"0\" id=\"1100001000376\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"武夷山公馆车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100037601\" num=\"1\" title=\"武夷山公馆车位检测4\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001008016001\" desc=\"\" domainId=\"0\" id=\"1100001000378\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"武夷山公馆车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100037801\" num=\"1\" title=\"武夷山公馆车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "                <Department coding=\"1001008016002\" domainId=\"0\" id=\"151\" name=\"武夷山供电公司赤岸城市充电站\" type=\"\"/>\n" +
                "            </Department>\n" +
                "        </Department>\n");stringBuilder.append(" <Department coding=\"1001009\" domainId=\"0\" id=\"10\" name=\"宁德供电公司\" type=\"\">\n" +
                "            <Department coding=\"1001009001\" domainId=\"0\" id=\"50\" name=\"宁德供电公司8#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009001\" desc=\"\" domainId=\"0\" id=\"1100001000590\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福鼎鼎融国际广场车位检测器2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059001\" num=\"1\" title=\"福鼎鼎融国际广场车位检测器2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009001\" desc=\"\" domainId=\"0\" id=\"1100001000594\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福鼎鼎融国际广场车位检测器1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059401\" num=\"1\" title=\"福鼎鼎融国际广场车位检测器1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009001\" desc=\"\" domainId=\"0\" id=\"1100001000596\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福鼎鼎融国际广场车位检测器3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059601\" num=\"1\" title=\"福鼎鼎融国际广场车位检测器3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009001\" desc=\"\" domainId=\"0\" id=\"1100001000599\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福鼎鼎融国际广场车位检测器4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100059901\" num=\"1\" title=\"福鼎鼎融国际广场车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001009001\" desc=\"\" domainId=\"0\" id=\"1100001000605\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"福鼎鼎融国际广场NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100060501\" num=\"1\" title=\"福鼎鼎融国际广场NVR\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060502\" num=\"2\" title=\"福鼎鼎融国际广场车位检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060503\" num=\"3\" title=\"福鼎鼎融国际广场车位检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060504\" num=\"4\" title=\"福鼎鼎融国际广场车位检测器3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100060505\" num=\"5\" title=\"福鼎鼎融国际广场车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" );stringBuilder.append("<Department coding=\"1001009002\" domainId=\"0\" id=\"51\" name=\"宁德供电公司7#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001009002\" desc=\"\" domainId=\"0\" id=\"1100001000612\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"福鼎鼎融国际广场NVR2\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100061201\" num=\"1\" title=\"福鼎鼎融国际广场NVR\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061202\" num=\"2\" title=\"福鼎鼎融国际广场车位检测器1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061203\" num=\"3\" title=\"福鼎鼎融国际广场车位检测器2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061204\" num=\"4\" title=\"福鼎鼎融国际广场车位检测器3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100061205\" num=\"5\" title=\"福鼎鼎融国际广场车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001009003\" domainId=\"0\" id=\"52\" name=\"宁德供电公司6#城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001009004\" domainId=\"0\" id=\"53\" name=\"宁德供电公司5#城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001009005\" domainId=\"0\" id=\"54\" name=\"宁德供电公司4#城市充电站\" type=\"\"/>\n" +
                "            <Department coding=\"1001009006\" domainId=\"0\" id=\"55\" name=\"宁德供电公司3#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001009006\" desc=\"\" domainId=\"0\" id=\"1100001000855\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"宁德市寿宁犀溪漂流游客集散中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100085501\" num=\"1\" title=\"宁德市寿宁犀溪漂流游客集散中心NVR\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100085502\" num=\"2\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100085503\" num=\"3\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100085504\" num=\"4\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100085505\" num=\"5\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009006\" desc=\"\" domainId=\"0\" id=\"1100001000857\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100085701\" num=\"1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009006\" desc=\"\" domainId=\"0\" id=\"1100001000859\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100085901\" num=\"1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009006\" desc=\"\" domainId=\"0\" id=\"1100001000861\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100086101\" num=\"1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009006\" desc=\"\" domainId=\"0\" id=\"1100001000865\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100086501\" num=\"1\" title=\"宁德市寿宁犀溪漂流游客集散中心车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001009007\" domainId=\"0\" id=\"56\" name=\"宁德供电公司2#城市充电站\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001009007\" desc=\"\" domainId=\"0\" id=\"1100001000835\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"宁德市周宁县九龙漈游客集散中心NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100083501\" num=\"1\" title=\"宁德市周宁县九龙漈游客集散中心NVR球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100083502\" num=\"2\" title=\"宁德市周宁县九龙漈游客集散中心车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100083503\" num=\"3\" title=\"宁德市周宁县九龙漈游客集散中心车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100083504\" num=\"4\" title=\"宁德市周宁县九龙漈游客集散中心车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100083505\" num=\"5\" title=\"宁德市周宁县九龙漈游客集散中心车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009007\" desc=\"\" domainId=\"0\" id=\"1100001000837\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100083701\" num=\"1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009007\" desc=\"\" domainId=\"0\" id=\"1100001000839\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100083901\" num=\"1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009007\" desc=\"\" domainId=\"0\" id=\"1100001000841\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100084101\" num=\"1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009007\" desc=\"\" domainId=\"0\" id=\"1100001000843\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100084301\" num=\"1\" title=\"宁德市周宁县九龙漈游客集散中心车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001009008\" domainId=\"0\" id=\"266\" name=\"福安闽东电力电器厂\" type=\"\">\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001009008\" desc=\"\" domainId=\"0\" id=\"1100001000791\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"福安闽东电力电器厂NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100079101\" num=\"1\" title=\"福安闽东电力电器厂360气象球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100079102\" num=\"2\" title=\"福安闽东电力电器厂车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100079103\" num=\"3\" title=\"福安闽东电力电器厂车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100079104\" num=\"4\" title=\"福安闽东电力电器厂车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100079105\" num=\"5\" title=\"福安闽东电力电器厂车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009008\" desc=\"\" domainId=\"0\" id=\"1100001000793\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福安闽东电力电器厂车位检测器1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100079301\" num=\"1\" title=\"福安闽东电力电器厂车位检测器1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009008\" desc=\"\" domainId=\"0\" id=\"1100001000797\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福安闽东电力电器厂车位检测器2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100079701\" num=\"1\" title=\"福安闽东电力电器厂车位检测器2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009008\" desc=\"\" domainId=\"0\" id=\"1100001000799\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福安闽东电力电器厂车位检测器3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100079901\" num=\"1\" title=\"福安闽东电力电器厂车位检测器3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009008\" desc=\"\" domainId=\"0\" id=\"1100001000801\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"福安闽东电力电器厂车位检测器4\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100080101\" num=\"1\" title=\"福安闽东电力电器厂车位检测器4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001009009\" domainId=\"0\" id=\"58\" name=\"沈海复线东狮山服务区（沈阳方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009009\" desc=\"\" domainId=\"0\" id=\"1100001000150\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务区往沈阳车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015001\" num=\"1\" title=\"东狮山服务区往沈阳车位检测1号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009009\" desc=\"\" domainId=\"0\" id=\"1100001000152\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"东狮山服务区往沈阳车位检测2号\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务器往沈阳车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015201\" num=\"1\" title=\"东狮山服务器往沈阳车位检测2号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009009\" desc=\"\" domainId=\"0\" id=\"1100001000154\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务区往沈阳车位检3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015401\" num=\"1\" title=\"东狮山服务区往沈阳车位检3号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009009\" desc=\"\" domainId=\"0\" id=\"1100001000156\" ip=\"192.168.1.14\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务区往沈阳车位检测4号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015601\" num=\"1\" title=\"东狮山服务区往沈阳车位检测4号\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"5\" alertout=\"0\" channel=\"5\" coding=\"1001009009\" desc=\"\" domainId=\"0\" id=\"1100001000158\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4,5$9004:1,2,3,4,5$9002:1,2,3,4,5$9007:1,2,3,4,5\" title=\"东狮山服务区往沈阳方向NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100015801\" num=\"1\" title=\"东狮山服务区往沈阳方向球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015802\" num=\"2\" title=\"东狮山服务区往沈阳方向车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015803\" num=\"3\" title=\"东狮山服务区往沈阳方向车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015804\" num=\"4\" title=\"东狮山服务区往沈阳方向车位检测3\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100015805\" num=\"5\" title=\"东狮山服务区往沈阳方向车位检测4\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"5\" title=\"报警输入5\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001009010\" domainId=\"0\" id=\"59\" name=\"沈海复线东狮山服务区（海口方向）充电站\" type=\"\">\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009010\" desc=\"\" domainId=\"0\" id=\"1100001000164\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务区往海口车位检测1\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100016401\" num=\"1\" title=\"东狮山服务区往海口车位检测1\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"4\" alertout=\"0\" channel=\"4\" coding=\"1001009010\" desc=\"\" domainId=\"0\" id=\"1100001000168\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4$9004:1,2,3,4$9002:1,2,3,4$9007:1,2,3,4\" title=\"东狮山服务区往海口方向NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100016801\" num=\"1\" title=\"东狮山服务区往海口方向球机\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100016802\" num=\"2\" title=\"东狮山服务区往海口方向车位检测1\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100016803\" num=\"3\" title=\"东狮山服务区往海口方向车位检测2\" type=\"0\"/>\n" +
                "                    <Channel camera=\"1\" id=\"110000100016804\" num=\"4\" title=\"东狮山服务区往海口方向车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009010\" desc=\"\" domainId=\"0\" id=\"1100001000170\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务区往海口车位检测2\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017001\" num=\"1\" title=\"东狮山服务区往海口车位检测2\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "                <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009010\" desc=\"\" domainId=\"0\" id=\"1100001000173\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"东狮山服务区往海口车位检测3\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                    <Channel camera=\"1\" id=\"110000100017301\" num=\"1\" title=\"东狮山服务区往海口车位检测3\" type=\"0\"/>\n" +
                "                    <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                    <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                </Device>\n" +
                "            </Department>\n" +
                "            <Department coding=\"1001009011\" domainId=\"0\" id=\"60\" name=\"霞浦县供电公司\" type=\"\">\n" +
                "                <Department coding=\"1001009011001\" domainId=\"0\" id=\"152\" name=\"霞浦县供电公司专用充电桩\" type=\"\">\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009011001\" desc=\"\" domainId=\"0\" id=\"1100001000133\" ip=\"192.168.1.11\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"霞浦县委车位检测1号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100013301\" num=\"1\" title=\"霞浦县委车位检测1号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009011001\" desc=\"\" domainId=\"0\" id=\"1100001000138\" ip=\"192.168.1.12\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"霞浦县委车位检测2号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100013801\" num=\"1\" title=\"霞浦县委车位检测2号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"1\" alertout=\"1\" channel=\"1\" coding=\"1001009011001\" desc=\"\" domainId=\"0\" id=\"1100001000140\" ip=\"192.168.1.13\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1$9004:1$9002:1$9007:1$9010:1\" title=\"霞浦县委车位检测3号\" type=\"device.type.IPC\" typeid=\"2\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100014001\" num=\"1\" title=\"霞浦县委车位检测3号\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarmout alertdev=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输出1\"/>\n" +
                "                    </Device>\n" +
                "                    <Device alert=\"4\" alertout=\"0\" channel=\"4\" coding=\"1001009011001\" desc=\"\" domainId=\"0\" id=\"1100001000146\" ip=\"192.168.1.9\" manufacturer=\"0\" model=\"\" password=\"\" port=\"0\" rights=\"9001:1,2,3,4$9004:1,2,3,4$9002:1,2,3,4$9007:1,2,3,4\" title=\"霞浦县委NVR\" type=\"device.type.NVR\" typeid=\"6\" user=\"\">\n" +
                "                        <Channel camera=\"1\" id=\"110000100014601\" num=\"1\" title=\"霞浦县委球机\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100014602\" num=\"2\" title=\"霞浦县委车位检测1\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100014603\" num=\"3\" title=\"霞浦县委车位检测2\" type=\"0\"/>\n" +
                "                        <Channel camera=\"1\" id=\"110000100014604\" num=\"4\" title=\"霞浦县委车位检测3\" type=\"0\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"1\" title=\"报警输入1\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"2\" title=\"报警输入2\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"3\" title=\"报警输入3\"/>\n" +
                "                        <Alarm alertgrade=\"1\" alerttype=\"1\" num=\"4\" title=\"报警输入4\"/>\n" +
                "                    </Device>\n" +
                "                </Department>\n" +
                "            </Department>\n" +
                "        </Department>\n" +
                "    <Department coding=\"1001010\" domainId=\"1\" id=\"328\" name=\"1111\" type=\"\"/>\n" +
                "<Department coding=\"1001011\" domainId=\"1\" id=\"329\" name=\"sdfasdfas\" type=\"\"/>\n" +
                "</Department>\n" +
                "</Organization>");


        Document document = cover2Document(stringBuilder.toString());
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());

        Element elementByCoding = recursionFindByCoding(document.getRootElement().element(Tree.Department.toString()), "1001009010");
        if(elementByCoding != null)
        {
            String name = elementByCoding.getName();
            System.out.println(name);
            String s = elementByCoding.asXML();
            System.out.println(s);
        }
        else
        {
            System.out.println("null");
        }

        Element recursionRemoveByCoding = recursionRemoveByCoding(document.getRootElement(), "1001009010");
        if(recursionRemoveByCoding != null)
        {
            String name = recursionRemoveByCoding.getName();
            System.out.println(name);
            String s = recursionRemoveByCoding.asXML();
            System.out.println("after remove" + s);
        }
        else
        {
            System.out.println("null");
        }

    }
}
