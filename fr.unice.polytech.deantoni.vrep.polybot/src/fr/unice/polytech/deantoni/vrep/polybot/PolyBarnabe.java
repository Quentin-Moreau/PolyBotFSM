package fr.unice.polytech.deantoni.vrep.polybot;

import java.util.ArrayList;

import fr.unice.polytech.deantoni.vrep.polybot.robot.PolyRob;
import fr.unice.polytech.deantoni.vrep.polybot.utils.Blob;

public class PolyBarnabe extends PolyRob{
	
	public static final float PI = 3.141592f;
	
	protected ArrayList<Blob> blobs;
	
	public static void main(String[] args) {
		PolyBarnabe rob = new PolyBarnabe("127.0.0.1", 19997);
	}
	
	public PolyBarnabe(String IP, int portNumber) {
		super(IP, portNumber);
		setupBarnabe();
	}
	
	
	protected void setupBarnabe() {
		this.start();
		//openGrip();
		sleep(100);
		blobs = this.getViewableBlobs();
		printTargets();
		if(blobs.size() > 0)
			moveTo(blobs.get(0).positionX, blobs.get(0).positionY);
		
	}
	
	protected void printTargets() {
		System.out.println("- Targets -");
		if (blobs.size() > 0) {
			for (Blob b: blobs) {
				System.out.println("x: " + b.positionX + ", y: " + b.positionY);
			}
		}
	}
	
	protected void rotateToAngle(float angle) {
		int speed = 2;
		float padding = 0.1f;
		if (this.getOrientation() - angle < 0) {
			speed = - 2;
		}
		while (this.getOrientation() > angle + padding || this.getOrientation() < angle - padding) {
			this.goCurved(speed, - speed);
			System.out.println(getOrientation() + " vs : " + (this.getOrientation() > (angle + padding)) + " - " + (this.getOrientation() < angle - padding) );
		}
		this.stop();
	}
	
	protected void moveTo(int x, int y) {
		
		if (x < this.getPosition().x) {
			rotateToAngle(PI);
			while (x < this.getPosition().x) {
				System.out.println(getPosition().x);
				goStraight(20);
			}
			this.stop();
		} else {
			rotateToAngle(0);
			while (x > this.getPosition().x) {
				System.out.println(getPosition().x);
				goStraight(20);
			}
			this.stop();
		}
		
		if (y < this.getPosition().y) {
			rotateToAngle(3*PI/2);
			while (x < this.getPosition().y) {
				System.out.println(getPosition().y);
				goStraight(20);
			}
			this.stop();
		} else {
			rotateToAngle(PI/2);
			while (y > this.getPosition().y) {
				System.out.println(getPosition().y);
				goStraight(20);
			}
			this.stop();
		}
	}
	
	protected void stop() {
		this.goStraight(0);
		this.sleep(500);
	}
	
	

}
