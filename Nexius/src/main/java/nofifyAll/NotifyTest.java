/**
 * FileName: NotifyTest
 * Author:   Administrator
 * Date:     2018/7/27 9:09
 * Description: notify notifyAll wait
 */
package nofifyAll;

import java.util.HashMap;
import java.util.Map;

public class NotifyTest
{
    public static Doctor Dr;
    public static Map<Integer, Patient> map = new HashMap<>();


    public static void main(String[] args) {


        NotifyTest.Dr = new Doctor() {
            @Override
            public void zhiBing(Patient patient) {
                /*synchronized (Dr)
                {
                  *//*  try {
                        //Dr.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*//*
                    System.out.println("Doctor is service for patient : "+patient.getClass().getName());
                    try {
                        Thread.sleep(5000);
                        System.out.println("Doctor service end--- next patient");

                        Dr.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                }*/
                Patient patientLock = map.get(patient.code);

                synchronized (patientLock)
                {
                    System.out.println("Doctor is service for patient : "+patient.getClass().getName()+patient.code);
                    try {
                        Thread.sleep(5000);
                        System.out.println("Doctor service end--- next patient");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                patient.getClass().getName();
            }
        };

        Patient patient1 = new Patient(1);
        Patient patient2 = new Patient(2);
        Patient patient3 = new Patient(3);

        map.put(1,patient1);
        map.put(2,patient2);
        map.put(3,patient3);

        new Thread(() -> patient1.kanBin()).start();
        new Thread(() -> patient1.kanBin()).start();
        new Thread(() -> patient2.kanBin()).start();
        new Thread(() -> patient3.kanBin()).start();


    }


}
class Patient{
    public Integer code;

    public Patient(Integer code) {
        this.code = code;
    }

    public void kanBin() {
        NotifyTest.Dr.zhiBing(this);
    }
}

interface Doctor
{
    public void zhiBing(Patient patient);
}

