/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author thanhtd
 */
public class Constants {
       public static final int NUM_RABBITMQ_CONNECTION = 1;
       public static final int WAIT_TIME = 5;//wait 5 seconds if having any errors with RabbitMq Connection
       public static int NUM_WORKERS = 1;
       public static final String WORKER_PREFIX="generator_worker";
}
