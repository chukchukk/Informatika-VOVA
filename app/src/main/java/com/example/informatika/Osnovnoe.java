package com.example.informatika;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Osnovnoe extends AppCompatActivity {
    public static int arr[][]=new int[8][8];
    public static boolean  arr1[][]=new boolean[8][8];//true-белые, false-черные
    public static int arr2[]=new int[32];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //создаем отображение всех необходимых элементов
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lastlayout);
        Button result=findViewById(R.id.result);
        final TextView vivod=findViewById(R.id.vivod);
        final EditText Colortext=findViewById(R.id.textColor);
        //обработка кнопки вывода результата на экран
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main();
                try {
                    //обработка белых
                    if(Colortext.getText().toString().equals("белые")){
                        //проверка на ввод белого цвета
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Вы правильно ввели цвет",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        int k=0;
                        String s="";
                        for(int i=0;i<8;i++){
                            for(int j=0;j<8;j++){
                                if(arr1[i][j]==true){
                                    arr2[k]=arr[i][j];
                                    k++;
                                }
                            }
                        }
                        //Подготовка для быстрой сортировки
                        k=0;
                        int max=arr2.length-1;
                        arr2=quickSort(arr2, k, max);
                        for(int i=0;i<arr2.length;i++){
                            String perevod=String.valueOf(arr2[i]);
                            s=s+" "+ perevod;
                        }
                        //вывод обработанного массива на экран
                        vivod.setText(s);
                    }

                    //обработка черных
                    else if(Colortext.getText().toString().equals("черные")){
                        //проверка на ввод черных
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Вы правильно ввели цвет",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        int k=0;
                        String s="";
                        for(int i=0;i<8;i++){
                            for(int j=0;j<8;j++){
                                if(arr1[i][j]==false){
                                    arr2[k]=arr[i][j];
                                    k++;
                                }
                            }
                        }
                        //Подготовка для быстрой сортировки
                        k=0;
                        int max=arr2.length-1;
                        arr2=quickSort(arr2, k, max);
                        for(int i=0;i<arr2.length;i++){
                            String perevod=String.valueOf(arr2[i]);
                            s=s+" "+ perevod;
                        }
                        //вывод обработанного массива на экран
                        vivod.setText(s);
                    }
                    //неправильный ввод
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Проверьте корректность введенного значения",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //реализация quick sort
    public static int[] quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return array;

        if (low >= high)
            return array;

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
        return array;
    }

    //разлиновка массива 8 на 8 в шахматном порядке с помощью bool-массива
    public void main(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                arr[i][j]= (int) (Math.random()*100);
                if ((i%2==0)&&(j%2==0)) arr1[i][j]=true;
                else if((i%2==0)&&(j%2!=0)) arr1[i][j]=false;
                else if((i%2!=0)&&(j%2==0)) arr1[i][j]=false;
                else arr1[i][j]=true;
            }
        }
    };
}
