using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Threading;

namespace Timer
{

    public partial class MainWindow : Window
    {
        DispatcherTimer timer = new DispatcherTimer { Interval = TimeSpan.FromSeconds(1) };
        bool isTimerPage = true;
        bool isEditing = false;
        bool notDisturb = false;

        List<TimerBtn> timers = new List<TimerBtn>();
        int selectedTimer = 0;
        
        List<AlarmBtn> alarms = new List<AlarmBtn>();
        int selectedAlarm = 0;

        public MainWindow()
        {
            InitializeComponent();
            this.timer.Tick += new EventHandler(UpdateContent);
            timer.Start();
        }
        public int GetActiveTimers()
        {
            int active = 0;
            foreach (TimerBtn timer in timers)
            {
                
                if (!timer.IsStopped())
                {
                    active++;
                }
            }
            return active;
        }
        public int GetActiveAlarms()
        {
            int active = 0;
            foreach (AlarmBtn alarm in alarms)
            {

                if (alarm.IsActive())
                {
                    active++;
                }
            }
            return active;
        }
        public void ToggleDisturbMode()
        {
            notDisturb = !notDisturb;
        }
        public void SetCounters()
        {
            if (isTimerPage)
            {
                TimersCounter.Content = "Active Timers: " + GetActiveTimers();
            }
            else
            {
                AlarmsCounter.Content = "Active Alarms: " + GetActiveAlarms();
            }
        }
        private void StartEditingMode()
        {
            TimerValue.Content = "00:00:00";
            ActivateBtn.Content = "START";
            SetTime.Text = "00:00:00";
            SetTime.Visibility = Visibility.Visible;
            isEditing = true;
        }
        private void StartEditingModeAlarms()
        {
            SetAlarm.IsEnabled = true;
            isEditing = true;
            SetAlarm.Text = "00:00";
            ActivateBtn2.Content = "ADD";
        }
        private void DisturbMode_Click(object sender, RoutedEventArgs e)
        {
            ToggleDisturbMode();
            if (notDisturb)
            {
                Disturb.Background = Brushes.Green;
                Disturb1.Background = Brushes.Green;
            } else
            {
                Disturb.Background = Brushes.Gray;
                Disturb1.Background = Brushes.Gray;
            }
        }
        private void UpdateContent(object sender, EventArgs e)
        {
            SetCounters();

            foreach (Button alarm in Alarms.Children)
            {
                if (alarm.Tag != null && ((String)alarm.Tag).Contains("AlarmBtn"))
                {
                    String content = alarm.Tag.ToString();
                    String numContent = new String(content.Where(Char.IsDigit).ToArray());
                    int index = Int32.Parse(numContent);

                    if (alarms[index].IsActive())
                    {
                        alarm.Background = Brushes.Green;
                        if (alarms[index].CheckAlarmEnd() && !notDisturb)
                        {
                            ActivateBtn2.Content = "ON";
                            MessageBox.Show("Alarm ended");
                        };
                    } else
                    {
                        alarm.Background = Brushes.Gray;
                    }
                }
            }
            if (!isEditing && !isTimerPage)
            {
                SetAlarm.Text = alarms[selectedAlarm].GetTime();
            }

            int i = 0;
            foreach (Button el in Timers.Children)
            {
                if (el.Tag != null && ((String) el.Tag).Contains("TimerBtn"))
                {
                    TimeSpan currentTimerTimeLeft = timers[i].getTime();
                    if (currentTimerTimeLeft == TimeSpan.Zero) {
                        if (!notDisturb)
                        {
                            SystemSounds.Exclamation.Play();
                            MessageBox.Show("Timer is Ringing!!!");
                        }
                        
                        Timers.Children.Remove(el);
                        
                        foreach (Button changebtn in Timers.Children)
                        {
                            if (changebtn.Tag != null && ((String)changebtn.Tag).Contains("TimerBtn")) {
                                String content = changebtn.Tag.ToString();
                                String numContent = new String(content.Where(Char.IsDigit).ToArray());
                                int index = Int32.Parse(numContent);
                                if (index > i)
                                {
                                    changebtn.Tag = "TimerBtn" + (index - 1);
                                }
                            } 
                        }
                        timers.RemoveAt(i);
                        i--;
                        
                        //If all timers out
                        if (timers.Count == 0)
                        {
                            StartEditingMode();
                        } 

                        selectedTimer = timers.Count - 1;

                       if (selectedTimer != -1 && timers[selectedTimer].IsStopped())
                        {
                            ActivateBtn.Content = "CONTINUE";
                        }
                        
                        return;
                    } else if (currentTimerTimeLeft < TimeSpan.FromSeconds(10))
                    {
                        el.Background = Brushes.Red;
                        el.FontWeight = FontWeights.Bold;
                        el.Content = currentTimerTimeLeft;
                        el.FontSize = 20;
                    } else 
                    {
                        el.Content = currentTimerTimeLeft;
                    }
                    
                    
                    if (!isEditing && isTimerPage)
                    {
                        TimerValue.Content = timers[selectedTimer].getTime().ToString();
                    }
                    timers[i].update();
                    i++;
                }
            }
            
            
        }
        private void AddBtn_Click(object sender, RoutedEventArgs e)
        {
            if (isTimerPage)
            {
                StartEditingMode();
            } else
            {
                StartEditingModeAlarms();
            }
            
        }
        private void ToggleTimerAndCreateIfNew(object sender, RoutedEventArgs e)
        {
            
           if ((String) ActivateBtn.Content == "START")
            {
                
                TimerBtn newTimer = new TimerBtn(SetTime.Text);
                timers.Add(newTimer);
                selectedTimer = timers.Count - 1;
                TimerValue.Content = timers[selectedTimer].getTime().ToString();
                ActivateBtn.Content = "STOP";
                SetTime.Visibility = Visibility.Hidden;

                Button newTimerBtn = new Button();
                newTimerBtn.Tag = "TimerBtn" + (timers.Count - 1);
                newTimerBtn.Click += SelectTimerClick;
                
                newTimerBtn.Content = timers[selectedTimer].getTime().ToString();
                Timers.Children.Add(newTimerBtn);
                

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
        private void ToggleAlarmAndCreateIfNew(object sender, RoutedEventArgs e)
        {

            if ((String)ActivateBtn2.Content == "ADD")
            {

                AlarmBtn newAlarm = new AlarmBtn(SetAlarm.Text);
                alarms.Add(newAlarm);
                selectedAlarm = alarms.Count - 1;
                ActivateBtn2.Content = "OFF";
                SetTime.Visibility = Visibility.Hidden;

                Button newAlarmBtn = new Button();
                newAlarmBtn.Tag = "AlarmBtn" + (alarms.Count - 1);
                newAlarmBtn.Click += SelectAlarmClick;

                newAlarmBtn.Content = alarms[selectedAlarm].GetTime();
                Alarms.Children.Add(newAlarmBtn);
                SetAlarm.IsEnabled = false;
                isEditing = false;
            }
            else
            {
                alarms[selectedAlarm].ToggleAlarm();
                if ((String)ActivateBtn2.Content == "OFF")
                {
                    ActivateBtn2.Content = "ON";
                }
                else
                {
                    ActivateBtn2.Content = "OFF";
                }
            }

        }
        private void SelectTimerClick(object sender, RoutedEventArgs e)
        {
            isEditing = false;
            SetTime.Visibility = Visibility.Hidden;
            String content = ((Button)e.OriginalSource).Tag.ToString();
            String numContent = new String(content.Where(Char.IsDigit).ToArray());
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
        private void SelectAlarmClick(object sender, RoutedEventArgs e)
        {
            isEditing = false;
            String content = ((Button)e.OriginalSource).Tag.ToString();
            String numContent = new String(content.Where(Char.IsDigit).ToArray());
            selectedAlarm = Int32.Parse(numContent);

            if (alarms[selectedAlarm].IsActive())
            {
                ActivateBtn2.Content = "OFF";
            }
            else
            {
                ActivateBtn2.Content = "ON";
            }
        }
        private void TimerBtn_Click(object sender, RoutedEventArgs e)
        {
            if (timers.Count == 0) StartEditingMode();         
            TimerBtn.Background = Brushes.Red;
            AlarmBtn.Background = Brushes.Orange;
            Alarms.Visibility = Visibility.Hidden;
            Timers.Visibility = Visibility.Visible;
            TimerDisplay.Visibility = Visibility.Visible;
            AlarmDisplay.Visibility = Visibility.Hidden;
            isTimerPage = true;
        }
        private void AlarmBtn_Click(object sender, RoutedEventArgs e)
        {
            if (alarms.Count == 0) StartEditingModeAlarms();
            isTimerPage = false;
            TimerBtn.Background = Brushes.Orange;
            AlarmBtn.Background = Brushes.Red;
            Alarms.Visibility = Visibility.Visible;
            Timers.Visibility = Visibility.Hidden;
            TimerDisplay.Visibility = Visibility.Hidden;
            AlarmDisplay.Visibility = Visibility.Visible;
        }
    }
}
