package com.example.mizmer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamActivity extends AppCompatActivity implements MyListener {

    Button bttn;
    FrameLayout frameLayout;
    ImageView ımageView;
    TextView textTest;

    public static ArrayList arrayList = new ArrayList();
    public static int[] array = new int[9];

    int answer1=0, answer2=0, answer3=0, answer4=0, answer5=0, answer6=0, answer7=0, answer8=0, answer9=0;
    public static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        frameLayout = (FrameLayout)findViewById(R.id.contentExamLayout);

        bttn =(Button)findViewById(R.id.button);

        ımageView=(ImageView)findViewById(R.id.examImage);

        textTest = (TextView)findViewById(R.id.textViewExam);

        textTest.setVisibility(View.INVISIBLE);


    }

    public void onClicked(View view){

        bttn.setVisibility(View.INVISIBLE);
        ımageView.setVisibility(View.INVISIBLE);

        Dtmm1Fragment dtmm1Fragment = new Dtmm1Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentExamLayout, dtmm1Fragment, dtmm1Fragment.getTag()).commit();


    }



    @Override
    public void sendValToList(int val) {

        final Bundle bundle1 = new Bundle();
        bundle1.putInt("forWhom",1);

//        array[i] = val;
//        i++;
//
//        textTest.setText(String.valueOf(array[i]));

        arrayList.add(val);
        if (!String.valueOf(arrayList.size()).equals(null))
            textTest.setText(String.valueOf(arrayList.get(i))); // TO SEE WHETHER WE CAN INSERT ELEMENTS TO ARRAYLIST OR NOT

        i++;


        if (i==9) {
            //bundle = new Bundle();
            //dtmm9Fragment = new Dtmm9Fragment();

            answer1 = Integer.parseInt(String.valueOf(arrayList.get(0)));
            answer2 = Integer.parseInt(String.valueOf(arrayList.get(1)));
            answer3 = Integer.parseInt(String.valueOf(arrayList.get(2)));
            answer4 = Integer.parseInt(String.valueOf(arrayList.get(3)));
            answer5 = Integer.parseInt(String.valueOf(arrayList.get(4)));
            answer6 = Integer.parseInt(String.valueOf(arrayList.get(5)));
            answer7 = Integer.parseInt(String.valueOf(arrayList.get(6)));
            answer8 = Integer.parseInt(String.valueOf(arrayList.get(7)));
            answer9 = Integer.parseInt(String.valueOf(arrayList.get(8)));

//            bundle.putInt("answerOf1",  answer1);   //SEND ARRAY TO Dtmm9Fragment'S BTNCLICKED METHOD
//            bundle.putInt("answerOf2",  answer2);
//            bundle.putInt("answerOf3",  answer3);
//            bundle.putInt("answerOf4",  answer4);
//            bundle.putInt("answerOf5",  answer5);
//            bundle.putInt("answerOf6",  answer6);
//            bundle.putInt("answerOf7",  answer7);
//            bundle.putInt("answerOf8",  answer8);
//            bundle.putInt("answerOf9",  answer9);
//            dtmm9Fragment.setArguments(bundle);


            int[] answersArray = new int[]{answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9};
            int max = 0;
            for (int i = 0 ; i < 9 ; i++){
                if(answersArray[i] > max)  max = answersArray[i];
            }

            // find the INDEXes of hihest rates
            ArrayList<Integer> equality = new ArrayList<>();
            for(int i = 0; i < 9; i++){
                if (answersArray[i] == max || answersArray[i] == (max - 1)){
                    equality.add(i+1); //INDEX OF MAX
                }
            }

            //***************************************** EQUALITY RULES
            if (equality.size() == 1){

                SonucFragment sonucFragment = new SonucFragment();
                bundle1.putInt("whichquestion",equality.get(0));
                sonucFragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.contentExamLayout, sonucFragment, sonucFragment.getTag()).commit();


//                            goToSonuc.putExtra("whichquestion", (int) equality.get(0) );
//                            startActivity(goToSonuc);
            }
            else if(equality.size()==2) {

                Asama2Fragment asama2Fragment = new Asama2Fragment();
                bundle1.putInt("equality1", equality.get(0));
                bundle1.putInt("equality2", equality.get(1));
                bundle1.putInt("size", equality.size());
                asama2Fragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.contentExamLayout, asama2Fragment, asama2Fragment.getTag()).commit();



//                            goToAsama2.putExtra("equality1",equality.get(0));
//                            goToAsama2.putExtra("equality2",equality.get(1));
//                            goToAsama2.putExtra("size",equality.size());
//                            startActivity(goToAsama2);
            }
            else if (equality.size()==3){
                Asama2Fragment asama2Fragment = new Asama2Fragment();
                bundle1.putInt("equality1", equality.get(0));
                bundle1.putInt("equality2", equality.get(1));
                bundle1.putInt("equality3", equality.get(2));
                bundle1.putInt("size", equality.size());
                asama2Fragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.contentExamLayout, asama2Fragment, asama2Fragment.getTag()).commit();



//                            goToAsama2.putExtra("equality1",equality.get(0));
//                            goToAsama2.putExtra("equality2",equality.get(1));
//                            goToAsama2.putExtra("equality3",equality.get(2));
//                            goToAsama2.putExtra("size",equality.size());
//                            startActivity(goToAsama2);
            }
            else{  //IF THERE IS EQUALITY MORE THAN 3 TIMES TAKE THE HIGHEST RATE
                SonucFragment sonucFragment = new SonucFragment();
                bundle1.putInt("whichquestion",equality.get(0));
                sonucFragment.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.contentExamLayout, sonucFragment, sonucFragment.getTag()).commit();




//                            goToSonuc.putExtra("whichquestion", (int) equality.get(0) );
//                            startActivity(goToSonuc);
            }


        }


    }


}
