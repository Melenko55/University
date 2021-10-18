using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Threading;

namespace Timer
{

    public partial class MainWindow : Window
    {
        DispatcherTimer timer = new DispatcherTimer { Interval = TimeSpan.FromSeconds(1) };
        List<TimerBtn> timers = new List<TimerBtn>();
        int selectedTimer = 0;
        bool isEditing = false;
        //AlarmBtn[] alarms;
        //int selectedAlarm = 0;

        public MainWindow()
        {
            InitializeComponent();
            this.timer.Tick += new EventHandler(UpdateContent);
            timer.Start();
        }

        private void StartEditingMode()
        {
            TimerValue.Content = "00:00:00";
            ActivateBtn.Content = "START";
            SetTime.Text = "00:00:00";
            SetTime.Visibility = Visibility.Visible;
            isEditing = true;
        }

        private void UpdateContent(object sender, EventArgs e)
        {
            if (timers.Count == 0) return;

            
            int i = 0;
            foreach (Button el in Timers.Children)
            {

                if ((String) el.Tag == ("TimerBtn" + i))
                {
                    String currentTimerTimeLeft = timers[i].getTime();
                    if (currentTimerTimeLeft == "00:00:00") {
                        MessageBox.Show("Timer" + i + " is Ringing!!!");
                        Timers.Children.Remove(el);
                        timers.RemoveAt(i);
                        if (timers.Count == 0)
                        {
                            StartEditingMode();
                        } 
                        selectedTimer = timers.Count - 1;
                       
                        
                        return;
                    } else if (currentTimerTimeLeft == "00:00:10")
                    {
                        el.Background = Brushes.Red;
                        el.Content = currentTimerTimeLeft;
                    } else 
                    {
                        el.Content = currentTimerTimeLeft;
                    }
                    
                    
                    if (!isEditing)
                    {
                        TimerValue.Content = timers[selectedTimer].getTime();
                    }
                    timers[i].update();
                    i++;
                }
            }
            
            
        }

        

        private void AddBtn_Click(object sender, RoutedEventArgs e)
        {
            StartEditingMode();
        }

        private void ToggleTimerAndCreateIfNew(object sender, RoutedEventArgs e)
        {
            
           if ((String) ActivateBtn.Content == "START")
            {
                
                TimerBtn newTimer = new TimerBtn(SetTime.Text);
                timers.Add(newTimer);
                selectedTimer = timers.Count - 1;
                TimerValue.Content = timers[selectedTimer].getTime();
                ActivateBtn.Content = "STOP";
                SetTime.Visibility = Visibility.Hidden;

                Button newTimerBtn = new Button();
                newTimerBtn.Tag = "TimerBtn" + (timers.Count - 1);
                newTimerBtn.Click += SelectTimerClick;
                
                newTimerBtn.Content = timers[selectedTimer].getTime();
                this.Timers.Children.Add(newTimerBtn);

                
                isEditing = false;
            }  else
            {
                timers[selectedTimer].toggleTimer();
                if ((String)ActivateBtn.Content == "STOP")
                {
                    ActivateBtn.Content = "CONTINUE";
                } else
                {
                    ActivateBtn.Content = "STOP";
                }
            } 
            
        }

        private void SelectTimerClick(object sender, RoutedEventArgs e)
        {
            String content = ((Button)e.OriginalSource).Tag.ToString();
            string numContent = new String(content.Where(Char.IsDigit).ToArray());
            
            selectedTimer = Int32.Parse(numContent);
            if (timers[selectedTimer].IsStopped()) 
            {
                ActivateBtn.Content = "CONTINUE";
            }
            else
            {
                ActivateBtn.Content = "STOP";
            }
        }

        private void TimerBtn_Click(object sender, RoutedEventArgs e)
        {

        }

        private void AlarmBtn_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
